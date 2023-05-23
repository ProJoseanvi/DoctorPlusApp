package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.API_DOCTORPLUS_BASE_URL;
import static com.example.doctorplus.common.Constantes.USER_TOKEN;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.doctorplus.common.Med;
import com.example.doctorplus.common.Patient;
import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;
import com.example.doctorplus.retrofit.DoctorPlusClient;
import com.example.doctorplus.retrofit.DoctorPlusAuthServices;
import com.example.doctorplus.retrofit.request.RequestCreateRecipe;
import com.example.doctorplus.retrofit.response.ResponseCreateRecipe;
import com.example.doctorplus.retrofit.response.ResponseListMeds;
import com.example.doctorplus.retrofit.response.ResponseListPatients;
import com.example.doctorplus.retrofit.response.ResponseRecipeId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create extends AppCompatActivity {

    private EditText dateCreationEdt, tomasEdt;
    private Button createRecipeBtn;
    private ImageView ivPantallaAnterior;
    private AutoCompleteTextView acMeds, acPatients;

    private String recipeId;
    private List<Med> meds;
    private List<Patient> patients;
    private String recipeDate;

    private Integer idPatientSelected;

    TextView tvNumeroAsignado, tvMensajeError;

    DoctorPlusAuthServices doctorPlusAuthServices;
    DoctorPlusClient doctorPlusClient;

    private void retrofitInit() {
        doctorPlusClient = DoctorPlusClient.getInstance();
        doctorPlusAuthServices = doctorPlusClient.getDoctorPlusIdRecipeRequest();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        acPatients = findViewById(R.id.autoCompletePatients);
        dateCreationEdt = findViewById(R.id.editTextDateCreation);
        acMeds = findViewById(R.id.autoCompleteMeds);
        tomasEdt = findViewById(R.id.editTextNumTomas);
        createRecipeBtn = findViewById(R.id.buttonCreateRecipe);
        tvNumeroAsignado = findViewById(R.id.textViewNumeroAsignado);
        tvMensajeError = findViewById(R.id.textViewMensajeError);
        ivPantallaAnterior = findViewById(R.id.imageViewLogoCreate);

        findViews();
        events();
        retrofitInit();

        generateRecipeId();
        generateListMeds();
        generateListPatients();
        generateRecipeDate();


        // añadimos click listener al botón
        createRecipeBtn.setOnClickListener(this::onClick);
        ivPantallaAnterior.setOnClickListener(this::onClick);


        ImageView imageViewLogoCreate = findViewById(R.id.imageViewLogoCreate);
        imageViewLogoCreate.setOnClickListener(v -> {
            Intent intent = new Intent(Create.this, MedicUser.class);
            startActivity(intent);
        });


    }


    private void generateListPatients() {
        Call<ResponseListPatients> callMeds = doctorPlusAuthServices.getPatients("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN));
        callMeds.enqueue(new Callback<ResponseListPatients>() {
            //creamos metodo para cuando hay respuesta del servidor
            @Override
            public void onResponse(@NonNull Call<ResponseListPatients> call, @NonNull Response<ResponseListPatients> response) {

                if (response.isSuccessful()) {
                    //Toast.makeText(Create.this, "Connection success!", Toast.LENGTH_SHORT).show();
                    patients = response.body().getPatients();
                    acPatients.setAdapter(new ArrayAdapter<>(Create.this,  android.R.layout.simple_dropdown_item_1line, patients));
                    acPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Patient p = (Patient) parent.getItemAtPosition(position);
                            idPatientSelected = p.getId();
                        }
                    });
                } else {
                    Toast.makeText(Create.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseListPatients> call, Throwable t) {

            }
        });
    }

    private void generateRecipeDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        recipeDate = sdf.format(cal.getTime());
        dateCreationEdt.setText(recipeDate);
    }

    private void generateListMeds() {
        Call<ResponseListMeds> callMeds = doctorPlusAuthServices.getMeds("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN));
        callMeds.enqueue(new Callback<ResponseListMeds>() {
            //creamos metodo para cuando hay respuesta del servidor
            @Override
            public void onResponse(@NonNull Call<ResponseListMeds> call, @NonNull Response<ResponseListMeds> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(Create.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Create.this, android.R.layout.simple_dropdown_item_1line, response.body().getMedsNames());
                    acMeds.setAdapter(adapter);

                } else {
                    Toast.makeText(Create.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListMeds> call, Throwable t) {

            }
        });
    }

    private void generateRecipeId() {
        Call<ResponseRecipeId> call = doctorPlusAuthServices.getRecipeId("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN));
        call.enqueue(new Callback<ResponseRecipeId>() {
            //creamos metodo para cuando hay respuesta del servidor
            @Override
            public void onResponse(@NonNull Call<ResponseRecipeId> call, @NonNull Response<ResponseRecipeId> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(Create.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();
                    tvNumeroAsignado.setText("");
                    tvNumeroAsignado.setText(response.body().getId());
                    recipeId = response.body().getId();
                } else {
                    Toast.makeText(Create.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseRecipeId> call, Throwable t) {

            }
        });
    }

    private void events() {
    }

    private void findViews() {

    }

    private void postData(String recipeId, Integer patientId, String date, String medication, String tomas) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_DOCTORPLUS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestCreateRecipe requestCreateRecipe = new RequestCreateRecipe(recipeId, patientId, date, medication, tomas);

        Call<ResponseCreateRecipe> call = doctorPlusAuthServices.createPost("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestCreateRecipe);


        call.enqueue(new Callback<ResponseCreateRecipe>() {
            @Override
            public void onResponse(@NonNull Call<ResponseCreateRecipe> call, @NonNull Response<ResponseCreateRecipe> response) {
                Toast.makeText(Create.this, "Datos añadidos a la API", Toast.LENGTH_SHORT).show();

                ResponseCreateRecipe responseFromAPI = response.body();
                Toast.makeText(Create.this, responseFromAPI.getMessage(), Toast.LENGTH_SHORT).show();
                if (responseFromAPI.getSuccess().equals("ok")) {
                    generateRecipeId();
                    generateListMeds();
                    generateListPatients();
                    generateRecipeDate();

                    tomasEdt.setText("");
                    acMeds.setText("");
                    acPatients.setText("");

                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseCreateRecipe> call, @NonNull Throwable t) {
                tvMensajeError.setText("Lo siento, revise si hay algún fallo!");
            }
        });
    }

    private void onClick(View v) {
        // validamos si campos de textos vacíos o no
        boolean error = false;


        /*if (dateCreationEdt.getText().toString().trim().isEmpty()) {
            //patientEdt.setBackgroundColor(Color.RED);
            ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo));
            patientEdt.setBackgroundTintList(colorStateList);
            error = true;
        } else {
            dateCreationEdt.setTextColor(Color.WHITE);
        }*/

        if (acMeds.getText().toString().trim().isEmpty()) {
            //patientEdt.setBackgroundColor(Color.RED);
            ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo));
            //patientEdt.setBackgroundTintList(colorStateList);

            if(this.meds.contains(acMeds.getText())){
                //todo guay sigue para adelante
                //limpio los mensajes de error
            }else{
                // muestro los mensajes de error
                // y evito que se mande la informacion al servidor
            }
            error = true;
        } else {
            acMeds.setTextColor(Color.WHITE);
        }
        if (tomasEdt.getText().toString().trim().isEmpty()) {
            //patientEdt.setBackgroundColor(Color.RED);
            ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojo));
            tomasEdt.setBackgroundTintList(colorStateList);
            error = true;
        } else {
            tomasEdt.setTextColor(Color.WHITE);

        }
        if (error) {
            tvMensajeError.setText("Por favor corrija los errores");
            tvMensajeError.setVisibility(View.VISIBLE);
            return;
        }
        tvMensajeError.setText("");
        tvMensajeError.setVisibility(View.INVISIBLE);


        postData(recipeId, idPatientSelected, dateCreationEdt.getText().toString(), acMeds.getText().toString(), tomasEdt.getText().toString());

    }

}






