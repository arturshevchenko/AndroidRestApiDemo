package com.example.petstorerestapi;

import androidx.annotation.NonNull;


import com.appspector.sdk.monitors.events.model.CustomEventPayload;

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

    class PetModelEvent implements CustomEventPayload {

        @Override
        public String getName() {
            return "Custom Event Pet";
        }

        @Override
        public String getCategory() {
            return "Pet DTO";
        }

        @Override
        public Map<String, Object> getPayload() {
            final Map<String, Object> payload = new HashMap<>();
            payload.put("date", new Date());
            payload.put("id", id);
            payload.put("name", name);
            payload.put("status", name);
            return payload;
        }
    }
}
