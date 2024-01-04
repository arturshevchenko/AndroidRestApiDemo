package com.example.petstorerestapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("pet/{id}")
    Call<PetModel> getPetById(@Path("id") int id);
}
