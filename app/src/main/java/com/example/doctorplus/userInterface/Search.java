package com.example.doctorplus.userInterface;

import static android.R.layout.simple_dropdown_item_1line;
import static com.example.doctorplus.common.Constantes.API_DOCTORPLUS_BASE_URL;
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

        generateListPatients();
        generateListDates();
        generateListId();


        btnSearch.setOnClickListener(this::onClick);
        ivPantallaRewind.setOnClickListener(this::onClick);

        
        ImageView imageViewLogoRewind = findViewById(R.id.imageViewLogoRewind);
        imageViewLogoRewind.setOnClickListener(v -> {
            Intent intent = new Intent(Search.this, MedicUser.class);
            startActivity(intent);
        });


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

    private void getSearchRecipe () {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_DOCTORPLUS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(recipeIdSelected, patientIdSelected, this.dateSelected);

        /*Call<ResponseSearchRecipe> call = doctorPlusAuthServices.getSearchRecipe("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestSearchRecipe);


        call.enqueue(new Callback<ResponseSearchRecipe>() {
            @Override
            public void onResponse(@NonNull Call<ResponseSearchRecipe> call, @NonNull Response<ResponseSearchRecipe> response) {
                Toast.makeText(Search.this, "Datos añadidos a la API", Toast.LENGTH_SHORT).show();

                ResponseSearchRecipe responseFromAPI = response.body();
                assert responseFromAPI != null;
                Toast.makeText(Search.this, responseFromAPI.getMessage(), Toast.LENGTH_SHORT).show();
                if (responseFromAPI.getSuccess().equals("ok")) {

                    generateListId();
                    generateListDates();
                    generateListPatients();


                    acNumberRecipe.setText("");
                    acPatient.setText("");
                    acDate.setText("");

                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseSearchRecipe> call, @NonNull Throwable t) {
                Toast.makeText(Search.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onClick(View v) {

    }


}




