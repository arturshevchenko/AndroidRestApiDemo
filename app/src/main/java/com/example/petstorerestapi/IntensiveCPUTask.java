package com.example.petstorerestapi;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntensiveCPUTask {

    public static void run(){
        LargeObjectArrays list = new LargeObjectArrays();
        list.addToList();
        Collections.sort(LargeObjectArrays.largeObjectList);
    }

}
class LargeObjectArrays{
    public static final List<Double> largeObjectList = new ArrayList<>();

    public void addToList() {
        for (int i = 0; i < 1_000_000; i++) {
            largeObjectList.add(Math.random());
        }
    }
}