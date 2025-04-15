package com.example.dialogs_bse_8a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPassengers;
    RecyclerView.LayoutManager manager;
    PassengerAdapter adapter;

    FloatingActionButton fabAddNewPassenger;
    String preferenceForDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        fabAddNewPassenger.setOnClickListener((v)->{

            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle("New Passenger");
            View dialogView = LayoutInflater.from(this)
                            .inflate(R.layout.edit_add_passenger_dialog_design, null, false);
            dialog.setView(dialogView);
            dialog.show();

            // hooks of dialog view
            ImageView ivPic = dialogView.findViewById(R.id.ivPref);
            EditText etName= dialogView.findViewById(R.id.etName);
            EditText etPhone = dialogView.findViewById(R.id.etPhone);
            Button btnCancel = dialogView.findViewById(R.id.btnCancel);
            Button btnAdd = dialogView.findViewById(R.id.btnAdd);

            btnCancel.setOnClickListener((v1)->{
                dialog.dismiss();
            });

            ivPic.setOnClickListener((v1)->{
                if(preferenceForDialog.equals("bus"))
                {
                    ivPic.setImageResource(R.drawable.icon_train);
                    preferenceForDialog = "train";
                }
                else
                {
                    ivPic.setImageResource(R.drawable.icon_bus);
                    preferenceForDialog = "bus";
                }

            });
            btnAdd.setOnClickListener((v1)->{
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();

                if(name.isEmpty())
                {
                    etName.setError("Enter the name");
                    return;
                }
                if(phone.isEmpty())
                {
                    etPhone.setError("Enter the phone");
                    return;
                }

                DataClass.data.add(new Passenger(name, preferenceForDialog, phone));
                adapter.notifyItemInserted(DataClass.data.size()-1);
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
        });

    }

    private void init()
    {
        rvPassengers = findViewById(R.id.rvPassengers);
        fabAddNewPassenger = findViewById(R.id.fabAddNewPassenger);
        manager = new LinearLayoutManager(this);
        rvPassengers.setLayoutManager(manager);
        rvPassengers.setHasFixedSize(true);
        adapter = new PassengerAdapter(this, DataClass.data);
        rvPassengers.setAdapter(adapter);
        preferenceForDialog = "bus";
    }
}