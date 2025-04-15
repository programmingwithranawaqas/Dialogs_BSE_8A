package com.example.dialogs_bse_8a;

public class Passenger {
    private String name;
    private String pref;
    private String phone;

    public Passenger() {
    }

    public Passenger(String name, String pref, String phone) {
        this.name = name;
        this.pref = pref;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
