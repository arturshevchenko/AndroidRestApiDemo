package com.example.petstorerestapi;

import androidx.annotation.NonNull;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PetModel {
    private final int id;
    private final String name;
    private final String status;

    public PetModel(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @NonNull
    public String toString() {
        return "PET: " + this.id + " " + this.name + " " + this.status;
    }

}
