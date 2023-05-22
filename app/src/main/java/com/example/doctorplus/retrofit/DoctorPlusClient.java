package com.example.doctorplus.retrofit;

import com.example.doctorplus.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Definiremos patron Singleton para la conexión a la API
//Creamos conexión a través de un objeto de la clase Retrofit y a través de ese objeto acceder a los diferentes servicios que hemos creado en la interfaz
public class DoctorPlusClient {
    private static DoctorPlusClient instance = null;
    private DoctorPlusService doctorPlusService;

    private DoctorPlusAuthServices doctorPlusIdRecipeRequest;

    private DoctorPlusAuthServices doctorPlusSearchRecipeRequest;

    private Retrofit retrofit;

    public DoctorPlusClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_DOCTORPLUS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        doctorPlusService = retrofit.create(DoctorPlusService.class);
        doctorPlusIdRecipeRequest = retrofit.create(DoctorPlusAuthServices.class);

    }
    //Patron Singleton
    public static DoctorPlusClient getInstance() {
        if (instance == null) {
            instance = new DoctorPlusClient();
        }
        return instance;
    }

    public DoctorPlusService getDoctorPlusService () {
        return doctorPlusService;
    }

    public DoctorPlusAuthServices getDoctorPlusIdRecipeRequest () {
        return doctorPlusIdRecipeRequest;
    }

    public DoctorPlusAuthServices getDoctorPlusSearchRecipeRequest () {
        return doctorPlusSearchRecipeRequest;
    }
}
