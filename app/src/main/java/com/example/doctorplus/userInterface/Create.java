package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.USER_TOKEN;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;
import com.example.doctorplus.retrofit.DoctorPlusClient;
import com.example.doctorplus.retrofit.DoctorPlusIdRecipeRequest;
import com.example.doctorplus.retrofit.DoctorPlusService;
import com.example.doctorplus.retrofit.response.ResponseAuth;
import com.example.doctorplus.retrofit.response.ResponseRecipeId;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Create extends AppCompatActivity {

    DoctorPlusIdRecipeRequest doctorPlusIdRecipeRequest;
    DoctorPlusClient doctorPlusClient;

    private void retrofitInit() {
        doctorPlusClient = DoctorPlusClient.getInstance();
        doctorPlusIdRecipeRequest = doctorPlusClient.getDoctorPlusIdRecipeRequest();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        retrofitInit();

        Call<ResponseRecipeId> call = doctorPlusIdRecipeRequest.getRecipeId("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN));

        call.enqueue(new Callback<ResponseRecipeId>() {


            //creamos metodo para cuando hay respuesta del servidor
            @Override
            public void onResponse(@NonNull Call<ResponseRecipeId> call, @NonNull Response<ResponseRecipeId> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(Create.this, response.body().getId(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Create.this, "NOOOOOOOOOOOOOO", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRecipeId> call, Throwable t) {

            }
        });
    }
}
