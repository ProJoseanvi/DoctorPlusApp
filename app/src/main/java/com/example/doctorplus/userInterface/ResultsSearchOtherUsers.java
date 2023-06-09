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
import com.example.doctorplus.retrofit.response.ResponseChangeState;
import com.example.doctorplus.retrofit.response.ResponseCreateRecipe;
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

        rgEstado.setOnCheckedChangeListener((group, checkedId) -> {
            System.out.println("ID: " + checkedId);
            if (checkedId == R.id.radioButtonCreada) {
                changeState(1); // Estado "creada"
            } else if (checkedId == R.id.radioButtonPreparada) {
                changeState(2); // Estado "preparada"
            } else if (checkedId == R.id.radioButtonEntregada) {
                changeState(3); // Estado "entregada"
            } else {
                Toast.makeText(ResultsSearchOtherUsers.this, "La he cagado!", Toast.LENGTH_LONG).show();
            }
        });
        
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
                    switch(recipe.getState()){
                        case (1):
                            rgEstado.check(R.id.radioButtonCreada);
                            break;
                        case (2):
                            rgEstado.check(R.id.radioButtonPreparada);
                            break;
                        case (3):
                            rgEstado.check(R.id.radioButtonEntregada);
                            break;
                    }

                } else {
                    Toast.makeText(ResultsSearchOtherUsers.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseRecipe> call, @NonNull Throwable t) {

            }
        });


    }

    public void changeState (final Integer state) {
        String idRecipe = SharedPreferencesManager.getSomeStringValue(RECIPE_ID);
        Call<ResponseChangeState> call = doctorPlusAuthServices.changeState("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), idRecipe, state);

        call.enqueue(new Callback<ResponseChangeState>() {
            @Override
            public void onResponse(@NonNull Call<ResponseChangeState> call, @NonNull Response<ResponseChangeState> response) {
                ResponseCreateRecipe responseFromAPI = response.body();
                assert responseFromAPI != null;
                if (responseFromAPI.getSuccess().equals("ok")) {
                    Toast.makeText(ResultsSearchOtherUsers.this, "Estado de la receta cambiado.", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ResultsSearchOtherUsers.this, "Error en cambio de estado.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseChangeState> call, @NonNull Throwable t) {
                // Mostrar mensaje de error al usuario
                Toast.makeText(ResultsSearchOtherUsers.this, "Error en la conexión inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.radioButtonCreada) {
            changeState(1); // Estado "creada"
        } else if (id == R.id.radioButtonPreparada) {
            changeState(2); // Estado "preparada"
        } else if (id == R.id.radioButtonEntregada) {
            changeState(3); // Estado "entregada"
        } else {
            Toast.makeText(ResultsSearchOtherUsers.this, "La he cagado!", Toast.LENGTH_LONG).show();
        }
    }

    }



