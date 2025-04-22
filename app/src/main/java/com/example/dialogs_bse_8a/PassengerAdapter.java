package com.example.dialogs_bse_8a;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder> {

    Context context;
    ArrayList<Passenger> passengers;
    String preferenceForDialog;

    public PassengerAdapter(Context c, ArrayList<Passenger> passengers)
    {
        this.context = c;
        this.passengers = passengers;
    }

    @NonNull
    @Override
    public PassengerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.single_passenger_list_item_design, parent, false);
        return new PassengerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerViewHolder holder, int position) {
        Passenger p = passengers.get(position);
        holder.tvName.setText(p.getName());
        holder.tvPhone.setText(p.getPhone());
        holder.ivDel.setOnClickListener((v)->{

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirmation Message");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Delete", (d, v1)->{
                DataClass.data.remove(position);
                notifyItemRemoved(position);
            });
            builder.setNegativeButton("No", (d, v1)->{

            });

            builder.create().show();
        });
        holder.ivEdit.setOnClickListener((v)->{
            editRecord(position);
        });

        if(p.getPref().equals("bus"))
        {
            holder.ivPic.setImageResource(R.drawable.icon_bus);
        }
        else
        {
            holder.ivPic.setImageResource(R.drawable.icon_train);
        }

    }

    public void editRecord(int position)
    {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Edit Passenger");
        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.edit_add_passenger_dialog_design, null, false);
        dialog.setView(dialogView);
        dialog.show();

        // hooks of dialog view
        ImageView ivPic = dialogView.findViewById(R.id.ivPref);
        EditText etName= dialogView.findViewById(R.id.etName);
        EditText etPhone = dialogView.findViewById(R.id.etPhone);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);

        Passenger p = DataClass.data.get(position);
        etName.setText(p.getName());
        etPhone.setText(p.getPhone());
        preferenceForDialog = p.getPref();
        btnAdd.setText("Update");

        if(preferenceForDialog.equals("bus"))
        {
            ivPic.setImageResource(R.drawable.icon_bus);
        }
        else
        {
            ivPic.setImageResource(R.drawable.icon_train);
        }


        btnCancel.setOnClickListener((v1)->{
            dialog.dismiss();
        });

        ivPic.setOnClickListener((v1)->{
            if(p.getPref().equals("bus"))
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

            p.setName(name);
            p.setPhone(phone);
            p.setPref(preferenceForDialog);

            notifyItemChanged(position);
            Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return passengers.size();
    }

    public class PassengerViewHolder extends RecyclerView.ViewHolder
    {
        // hooks of item
        TextView tvName, tvPhone;
        ImageView ivPic, ivEdit, ivDel;
        public PassengerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            ivPic = itemView.findViewById(R.id.ivPic);
            ivEdit = itemView.findViewById(R.id.ivEdit);

            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
