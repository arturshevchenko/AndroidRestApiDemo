package com.example.petstorerestapi;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class IntensiveMemoryTask {

    public static void run() {
        Log.d("MemoryIntensiveTask", "Started Memory filling");
        LargeObject newObject = new LargeObject();
        newObject.addToList();
        Log.d("Object", "Finished");
    }

}

class LargeObject {
    private static final List<Double> largeObjectList = new ArrayList<>();

    public void addToList() {
        for (int i = 0; i < 1_000_000; i++) {
            largeObjectList.add(Math.random());
        }
    }
}

