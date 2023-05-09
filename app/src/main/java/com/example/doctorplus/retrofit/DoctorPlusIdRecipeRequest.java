package com.example.doctorplus.retrofit;

//Registraremos aqui tooos los servicios de la API

import com.example.doctorplus.retrofit.request.RequestLogin;
import com.example.doctorplus.retrofit.response.ResponseAuth;
import com.example.doctorplus.retrofit.response.ResponseRecipeId;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DoctorPlusIdRecipeRequest {

    //Aqui añadimos nuestro primer servicio el POST para hacer Login
    @GET("recetaId")
    Call<ResponseRecipeId> getRecipeId (@Header("Authorization") String authHeader);

}
