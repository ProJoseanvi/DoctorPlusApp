package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.DATE;
import static com.example.doctorplus.common.Constantes.PATIENT_ID;
import static com.example.doctorplus.common.Constantes.RECIPE_ID;
import static com.example.doctorplus.common.Constantes.USER_TOKEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsSearchOtherUsers extends AppCompatActivity {

    DoctorPlusClient doctorPlusClient;
    DoctorPlusAuthServices doctorPlusAuthServices;

    TextView tvRecipe;
    TextView tvPacient;
    TextView tvDate;
    TextView tvMeds;
    TextView tvNumberTomas;

    RadioGroup rgEstado;

    RadioButton rbPreparada,rbEntregada,rbCreada;
    private boolean rbEntregadaPressed;

    private void retrofitInit() {
        doctorPlusClient = DoctorPlusClient.getInstance();
        doctorPlusAuthServices = doctorPlusClient.getDoctorPlusIdRecipeRequest();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_search_other_users);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        tvRecipe = findViewById(R.id.textViewNumReceta3);
        tvPacient = findViewById(R.id.textViewNombrePaciente);
        tvDate = findViewById(R.id.textViewFecha3);
        tvMeds = findViewById(R.id.textViewMed);
        tvNumberTomas = findViewById(R.id.textViewTomas2);
        rbPreparada = findViewById(R.id.radioButtonPreparada);
        rbEntregada = findViewById(R.id.radioButtonEntregada);
        rbCreada = findViewById(R.id.radioButtonCreada);
        rgEstado = findViewById(R.id.radioGroupEstado);

        findViews();
        retrofitInit();
        getRecipeInfo();


        ImageView ivPantallaAnterior = findViewById(R.id.imageViewLogoReverse2);
        
        ivPantallaAnterior.setOnClickListener(this::onClick);

        ImageView imageViewLogoCreate = findViewById(R.id.imageViewLogoReverse2);
        imageViewLogoCreate.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsSearchOtherUsers.this, OtherUsers.class);
            startActivity(intent);
        });

        RadioGroup radioGroup = findViewById(R.id.radioGroupEstado);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            rbCreada.setEnabled(checkedId != R.id.radioButtonCreada);
            rbPreparada.setEnabled(checkedId != R.id.radioButtonPreparada);
            rbEntregada.setEnabled(checkedId != R.id.radioButtonCreada);
        });
        
    }



    private void onClick(View view) {
    }

    private void findViews() {

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
                    Toast.makeText(ResultsSearchOtherUsers.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseRecipe> call, @NonNull Throwable t) {

            }
        });


    }

    }



