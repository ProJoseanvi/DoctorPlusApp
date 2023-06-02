package com.example.doctorplus.userInterface;

import static android.R.layout.simple_dropdown_item_1line;
import static com.example.doctorplus.common.Constantes.API_DOCTORPLUS_BASE_URL;
import static com.example.doctorplus.common.Constantes.ROLE_MEDIC;
import static com.example.doctorplus.common.Constantes.USER_TOKEN;

import android.R.layout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.R;
import com.example.doctorplus.common.Constantes;
import com.example.doctorplus.common.Patient;
import com.example.doctorplus.common.SharedPreferencesManager;
import com.example.doctorplus.retrofit.DoctorPlusAuthServices;
import com.example.doctorplus.retrofit.DoctorPlusClient;
import com.example.doctorplus.retrofit.request.RequestSearchRecipe;
import com.example.doctorplus.retrofit.response.ResponseListPatients;
import com.example.doctorplus.retrofit.response.ResponseListRecipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Search extends AppCompatActivity implements View.OnClickListener {

    private List<Patient> patients;
    private List<String> dates;

    private List<String> recipesIds;

    private AutoCompleteTextView acDate, acNumberRecipe, acPatient;

    private Integer patientIdSelected;
    private String dateSelected;
    private String recipeIdSelected;


    Button btnSearch;
    ImageView ivPantallaRewind;

    DoctorPlusAuthServices doctorPlusAuthServices;
    DoctorPlusClient doctorPlusClient;


    private void retrofitInit() {
        doctorPlusClient = DoctorPlusClient.getInstance();
        doctorPlusAuthServices = doctorPlusClient.getDoctorPlusIdRecipeRequest();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        }

        btnSearch = findViewById(R.id.buttonBuscar);
        acNumberRecipe = findViewById(R.id.autoCompleteRecipe);
        acPatient = findViewById(R.id.autoCompletePatient);
        acDate = findViewById(R.id.autoCompleteDate);
        ivPantallaRewind = findViewById(R.id.imageViewLogoRewind);

        findViews();
        events();
        retrofitInit();

        generateListId();
        generateListDates();
        generateListPatients();

        btnSearch.setOnClickListener(this);
        ivPantallaRewind.setOnClickListener(this);


        ImageView imageViewLogoRewind = findViewById(R.id.imageViewLogoRewind);
        imageViewLogoRewind.setOnClickListener(v -> {
            Intent intent;
            String role =  SharedPreferencesManager.getSomeStringValue(Constantes.USER_ROLE);
            if(ROLE_MEDIC.equals(role)) {
                intent = new Intent(this, MedicUser.class);
            }else{// if(ROLE_OTHERS.equals(role))
                intent = new Intent(this, OtherUsers.class);
            }
            startActivity(intent);
            finish();
        });


        if(SharedPreferencesManager.getSomeStringValue(Constantes.DELETE_MESSAGE) != null){
            Toast.makeText(Search.this, SharedPreferencesManager.getSomeStringValue(Constantes.DELETE_MESSAGE), Toast.LENGTH_LONG).show();
            SharedPreferencesManager.setSomeStringValue(Constantes.DELETE_MESSAGE, null);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonBuscar) {
            goToResult();
        }
    }



    private void events() {
    }
    private void findViews() {
    }


    private void generateListId() {
        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(recipeIdSelected, patientIdSelected, dateSelected);

        Call<ResponseListRecipes> callIds = doctorPlusAuthServices.getIds("Bearer "
                + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestSearchRecipe);
        callIds.enqueue(new Callback<ResponseListRecipes>() {
            @Override
            public void onResponse(@NonNull Call<ResponseListRecipes> call, @NonNull Response<ResponseListRecipes> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    recipesIds = response.body().getIds();
                    acNumberRecipe.setAdapter(new ArrayAdapter<>(Search.this, layout.simple_dropdown_item_1line, recipesIds));
                    acNumberRecipe.setOnItemClickListener((parent, view, position, id) -> {
                        String p = (String) parent.getItemAtPosition(position);
                        recipeIdSelected = p;
                        generateListDates();
                        generateListPatients();
                    });
                } else {
                    Toast.makeText(Search.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseListRecipes> call, @NonNull Throwable t) {

            }

        });

    }


    private void generateListPatients() {
        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(recipeIdSelected, patientIdSelected, dateSelected);
            Call<ResponseListPatients> call = doctorPlusAuthServices.getPatientsByRecipe("Bearer "
                    + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestSearchRecipe);
        call.enqueue(new Callback<ResponseListPatients>() {
            @Override
            public void onResponse(@NonNull Call<ResponseListPatients> call, @NonNull Response<ResponseListPatients> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    patients = response.body().getPatients();
                    acPatient.setAdapter(new ArrayAdapter<>(Search.this, simple_dropdown_item_1line, patients));
                    acPatient.setOnItemClickListener((parent, view, position, id) -> {
                        Patient p = (Patient) parent.getItemAtPosition(position);
                        patientIdSelected = p.getId();
                        generateListDates();
                        generateListId();
                    });
                } else {
                    Toast.makeText(Search.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseListPatients> call, @NonNull Throwable t) {

            }

        });

    }

    private void generateListDates() {
        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(recipeIdSelected, patientIdSelected, dateSelected);
        Call<ResponseListRecipes> call = doctorPlusAuthServices.getDates("Bearer "
                + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestSearchRecipe);

        call.enqueue(new Callback<ResponseListRecipes>() {
            @Override
            public void onResponse(@NonNull Call<ResponseListRecipes> call, @NonNull Response<ResponseListRecipes> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    dates = response.body().getDates();
                    acDate.setAdapter(new ArrayAdapter<>(Search.this, simple_dropdown_item_1line, dates));
                    acDate.setOnItemClickListener((parent, view, position, id) -> {
                        dateSelected = (String) parent.getItemAtPosition(position);
                        generateListPatients();
                        generateListId();
                    });
                } else {
                    Toast.makeText(Search.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseListRecipes> call, @NonNull Throwable t) {

            }

        });

    }

    private void goToResult() {
        SharedPreferencesManager.setSomeStringValue(Constantes.DATE, this.dateSelected);
        SharedPreferencesManager.setSomeStringValue(Constantes.RECIPE_ID, this.recipeIdSelected);
        SharedPreferencesManager.setSomeStringValue(Constantes.PATIENT_ID, String.valueOf(this.patientIdSelected));
        String role =  SharedPreferencesManager.getSomeStringValue(Constantes.USER_ROLE);
        Intent intent;
        if(ROLE_MEDIC.equals(role)) {
            intent = new Intent(this, ResultsSearchMedic.class);
        }else{// if(ROLE_OTHERS.equals(role))
            intent = new Intent(this, ResultsSearchOtherUsers.class);
        }
        startActivity(intent);
        finish();
    }

    private void getSearchRecipe () {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_DOCTORPLUS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(recipeIdSelected, patientIdSelected, this.dateSelected);

    }


}





