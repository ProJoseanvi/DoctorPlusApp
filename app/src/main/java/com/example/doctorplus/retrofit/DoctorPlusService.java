package com.example.doctorplus.retrofit;

//Registraremos aqui tooos los servicios de la API

import com.example.doctorplus.retrofit.request.RequestLogin;
import com.example.doctorplus.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DoctorPlusService {

    //Aqui añadimos nuestro primer servicio el POST para hacer Login, son servicios sin securizar
    @POST("login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);
}
