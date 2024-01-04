package com.example.petstorerestapi;

import androidx.annotation.NonNull;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PetModel {
    private int id;
    private String name;
    private String status;

    public PetModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    public String toString(){
        return "PET: " + this.id + " " + this.name + " " + this.status;
    }

}
