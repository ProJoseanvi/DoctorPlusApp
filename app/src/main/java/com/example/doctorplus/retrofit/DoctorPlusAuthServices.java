package com.example.doctorplus.retrofit;

//Registraremos aqui tooos los servicios de la API

import com.example.doctorplus.retrofit.request.RequestCreateRecipe;
import com.example.doctorplus.retrofit.request.RequestSearchRecipe;
import com.example.doctorplus.retrofit.response.ResponseCreateRecipe;
import com.example.doctorplus.retrofit.response.ResponseListMeds;
import com.example.doctorplus.retrofit.response.ResponseListPatients;
import com.example.doctorplus.retrofit.response.ResponseRecipeId;
import com.example.doctorplus.retrofit.response.ResponseSearchRecipe;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DoctorPlusAuthServices {

    //Aqui agrupamos todos los servicios securizados en forma de interfaz
    @GET("recipe/id")
    Call<ResponseRecipeId> getRecipeId (@Header("Authorization") String authHeader);

    @POST("recipe/create")
    Call<ResponseCreateRecipe>  createPost (@Header("Authorization") String authHeader, @Body RequestCreateRecipe requestCreateRecipe);

    @GET("SearchReceta")
    Call<ResponseSearchRecipe> getSearchRecipe (@Header("Authorization") String authHeader, @Body RequestSearchRecipe requestSearchRecipe);

    @GET("meds/list")
    Call<ResponseListMeds> getMeds (@Header("Authorization") String authHeader);

    @GET("patients/list")
    Call<ResponseListPatients> getPatients(@Header("Authorization") String authHeader);
}



