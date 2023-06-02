package com.example.doctorplus.retrofit;

//Registraremos aqui tooos los servicios de la API

import com.example.doctorplus.retrofit.request.RequestCreateRecipe;
import com.example.doctorplus.retrofit.request.RequestSearchRecipe;
import com.example.doctorplus.retrofit.response.ResponseCreateRecipe;
import com.example.doctorplus.retrofit.response.ResponseDeleteRecipe;
import com.example.doctorplus.retrofit.response.ResponseListMeds;
import com.example.doctorplus.retrofit.response.ResponseListPatients;
import com.example.doctorplus.retrofit.response.ResponseListRecipes;
import com.example.doctorplus.retrofit.response.ResponseRecipe;
import com.example.doctorplus.retrofit.response.ResponseRecipeId;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DoctorPlusAuthServices {

    //Aqui agrupamos todos los servicios securizados en forma de interfaz

    @GET("recipe/id")
    Call<ResponseRecipeId> getRecipeId (@Header("Authorization") String authHeader);

    @POST("recipe/create")
    Call<ResponseCreateRecipe> createPost (@Header("Authorization") String authHeader, @Body RequestCreateRecipe requestCreateRecipe);

    @GET("meds/list")
    Call<ResponseListMeds> getMeds (@Header("Authorization") String authHeader);

    @GET("patient/list")
    Call<ResponseListPatients> getPatients (@Header("Authorization") String authHeader);

    @POST("patient/listByRecipe")
    Call<ResponseListPatients> getPatientsByRecipe (@Header("Authorization") String authHeader, @Body RequestSearchRecipe requestSearchRecipe);

    @POST("recipe/dates")
    Call<ResponseListRecipes> getDates (@Header("Authorization") String authHeader, @Body RequestSearchRecipe requestSearchRecipe);

    @POST("recipe/ids")
    Call<ResponseListRecipes> getIds (@Header("Authorization") String authHeader, @Body RequestSearchRecipe requestSearchRecipe);

    @POST("recipe/get")
    Call<ResponseRecipe> getRecipe (@Header("Authorization") String authHeader, @Body RequestSearchRecipe requestSearchRecipe);

    @DELETE("recipe/delete/{idRecipe}")
    Call<ResponseDeleteRecipe> deleteRecipe (@Header("Authorization") String authHeader, @Path("idRecipe") String idRecipe);

    @PUT("recipe/change/{idRecipe}/{state}")
    Call<ResponseDeleteRecipe> changeState (@Header("Authorization") String authHeader, @Path("idRecipe") String idRecipe, @Path("state") Integer state);
}



