package com.example.petstorerestapi;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText edpetID;
    EditText edpetName;
    EditText edpetStatus;

    EditText edfindPetID;

    Button btnSubmit;
    Button btnSearch;
    Button btnCPUTask;
    Button btnMemoryTask;

    TextView tvResult;
    TextView tvResultFind;

    int petID;
    String petName = null;
    String petStatus = null;
    int findPetID;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://petstore.swagger.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService apiService = retrofit.create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edpetID = findViewById(R.id.pet_id);
        edpetName = findViewById(R.id.pet_name);
        edpetStatus = findViewById(R.id.status);
        btnSubmit = findViewById(R.id.submit_btn);
        tvResult = findViewById(R.id.result);
        tvResultFind = findViewById(R.id.result_find);

        edfindPetID = findViewById(R.id.find_pet_id);
        btnSearch = findViewById(R.id.find_pet_btn);
        btnCPUTask = findViewById(R.id.cpu_task_find);
        btnMemoryTask = findViewById(R.id.mem_task_find);

        btnSubmit.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnCPUTask.setOnClickListener(this);
        btnMemoryTask.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_btn) {
            petID = Integer.parseInt(edpetID.getText().toString());
            petName = edpetName.getText().toString();
            petStatus = edpetStatus.getText().toString();

            PetModel pet = new PetModel();
            pet.setId(petID);
            pet.setName(petName);
            pet.setStatus(petStatus);

            // crete pet
            try {
                createPet(pet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.find_pet_btn) {
            findPetID = Integer.parseInt(edfindPetID.getText().toString());

            try {
                getPetById(findPetID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.cpu_task_find){
            IntensiveCPUTask.run();
        }
        if (v.getId() == R.id.mem_task_find){
            IntensiveMemoryTask.run();
        }
    }

    public void createPet(PetModel pet) throws IOException {
        URL url = NetworkUtils.buildUrl();
        Log.i("APIS_LOGS", "CALL API");
        new DataTask(pet).execute(url);
    }

    public void getPetById(int petID) throws IOException {
//        URL url = NetworkUtils.buildUrl();
//        Log.i("API", "CALL API");
//        new DataTaskGet(petID).execute(url);

        Call<PetModel> call = apiService.getPetById(petID);
        call.enqueue(new Callback<PetModel>() {
            @Override
            public void onResponse(Call<PetModel> call, Response<PetModel> response) {
                if (response.isSuccessful()) {
                    PetModel pet = response.body();

                    Log.i("APIS_LOGS", "CALL API");
                    Log.i("APIS_LOGS", response.body().toString());
                    tvResultFind.setText("PET IS:" + pet.toString());
                } else {
                    Log.i("APIS_LOGS", " NO PET FOUND");
                    Log.i("APIS_LOGS", response.toString());
                    tvResultFind.setText("NO PET FOUND");
                }
            }

            @Override
            public void onFailure(Call<PetModel> call, Throwable t) {
                Log.i("APIS_LOGS", " FAILED TO CALL API");
            }
        });
    }

    public class DataTask extends AsyncTask<URL, Void, String> {
        PetModel pet;

        DataTask(PetModel pet) {
            this.pet = pet;
        }

        @Override
        protected String doInBackground(URL... urls) {
            String data = null;
            try {
                data = NetworkUtils.createPet(this.pet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            printResAsString(s);
        }

        public void printResAsString(String data) {
            JSONObject myObject = null;
            try {
                myObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tvResult.setText(myObject.toString());
        }
    }


    public class DataTaskGet extends AsyncTask<URL, Void, String> {
        int petID;

        DataTaskGet(int petID) {
            this.petID = petID;
        }

        @Override
        protected String doInBackground(URL... urls) {
            String data = null;
            try {
                data = NetworkUtils.getPet(this.petID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            printResAsString(s);
        }

        public void printResAsString(String data) {
            JSONObject myObject = null;
            try {
                myObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tvResultFind.setText(myObject.toString());
        }
    }
}