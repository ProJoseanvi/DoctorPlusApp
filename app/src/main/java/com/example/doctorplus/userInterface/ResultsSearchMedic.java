package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.DATE;
import static com.example.doctorplus.common.Constantes.PATIENT_ID;
import static com.example.doctorplus.common.Constantes.RECIPE_ID;
import static com.example.doctorplus.common.Constantes.USER_TOKEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;
import com.example.doctorplus.retrofit.DoctorPlusAuthServices;
import com.example.doctorplus.retrofit.DoctorPlusClient;
import com.example.doctorplus.retrofit.request.RequestSearchRecipe;
import com.example.doctorplus.retrofit.response.ResponseRecipe;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsSearchMedic extends AppCompatActivity implements View.OnClickListener {


    DoctorPlusClient doctorPlusClient;
    DoctorPlusAuthServices doctorPlusAuthServices;

    TextView tvRecipe;
    TextView tvPacient;
    TextView tvDate;
    TextView tvMeds;
    TextView tvNumberTomas;

    private void retrofitInit() {
        doctorPlusClient = DoctorPlusClient.getInstance();
        doctorPlusAuthServices = doctorPlusClient.getDoctorPlusIdRecipeRequest();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_search_medic);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tvRecipe = findViewById(R.id.textViewRecipe);
        tvPacient = findViewById(R.id.textViewPacient);
        tvDate = findViewById(R.id.textViewDate);
        tvMeds = findViewById(R.id.textViewMeds);
        tvNumberTomas = findViewById(R.id.textViewNumberTomas);
        ImageView ivLogoReverse = findViewById(R.id.imageViewLogoReverse);
        retrofitInit();

        getRecipeInfo();

        Button buttonCreaReceta = findViewById(R.id.buttonDelReceta);
        buttonCreaReceta.setOnClickListener(this);

        ivLogoReverse.setOnClickListener(this::onClick);

        ImageView imageViewLogoCreate = findViewById(R.id.imageViewLogoReverse);
        imageViewLogoCreate.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsSearchMedic.this, MedicUser.class);
            startActivity(intent);
        });

    }

    private void getRecipeInfo() {
        String recipeIdSelected = SharedPreferencesManager.getSomeStringValue(RECIPE_ID);
        String dateSelected = SharedPreferencesManager.getSomeStringValue(DATE);
        Integer patientIdSelected = Integer.valueOf(SharedPreferencesManager.getSomeStringValue(PATIENT_ID));
        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(recipeIdSelected, patientIdSelected, dateSelected);

        Call<ResponseRecipe> callRecipe = doctorPlusAuthServices.getRecipe("Bearer "
                + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestSearchRecipe);
        callRecipe.enqueue(new Callback<ResponseRecipe>() {
            @Override
            public void onResponse(@NonNull Call<ResponseRecipe> call, @NonNull Response<ResponseRecipe> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    ResponseRecipe recipe = response.body();
                    tvRecipe.setText(recipe.getId());
                    tvPacient.setText(recipe.getNamePatient());
                    tvDate.setText(recipe.getDate());
                    tvMeds.setText(recipe.getMed());
                    tvNumberTomas.setText(recipe.getTakes());
                    //controlState.setValue(recipe.getState());
                } else {
                    Toast.makeText(ResultsSearchMedic.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseRecipe> call, @NonNull Throwable t) {

            }

        });
    }


    @Override
    public void onClick(View v) {

    }
}

