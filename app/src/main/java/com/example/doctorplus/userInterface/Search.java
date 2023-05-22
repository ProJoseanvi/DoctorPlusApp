package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.API_DOCTORPLUS_BASE_URL;
import static com.example.doctorplus.common.Constantes.USER_TOKEN;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;
import com.example.doctorplus.retrofit.DoctorPlusAuthServices;
import com.example.doctorplus.retrofit.DoctorPlusClient;
import com.example.doctorplus.retrofit.request.RequestSearchRecipe;
import com.example.doctorplus.retrofit.response.ResponseSearchRecipe;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity implements View.OnClickListener {

    Button btnSearch;
    EditText etNumberRecipe, etNumberUser, etPacientName, etDate, etNumberTomas;
    AutoCompleteTextView acMedicines;

    DoctorPlusAuthServices doctorPlusAuthServices;
    DoctorPlusClient doctorPlusClient;
    private Object v;

    private void retrofitInit() {
        doctorPlusClient = DoctorPlusClient.getInstance();
        doctorPlusAuthServices = doctorPlusClient.getDoctorPlusSearchRecipeRequest();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        }

        btnSearch = findViewById(R.id.buttonBuscar);
        etNumberRecipe = findViewById(R.id.editTextNumerosReceta);
        etNumberUser = findViewById(R.id.editTextNumberUser);
        etPacientName = findViewById(R.id.editTextPacienteNombre);
        etDate = findViewById(R.id.editTextDate);
        acMedicines = findViewById(R.id.autoCompleteMedicines);
        etNumberTomas = findViewById(R.id.editTextNumberTomas);


        retrofitInit();
        findViews();
        events();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, MEDICINES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteMedicines);
        textView.setAdapter(adapter);





        RequestSearchRecipe requestSearchRecipe = null;
        Call<ResponseSearchRecipe> call = doctorPlusAuthServices.getSearchRecipe("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), null);

        call.enqueue(new Callback<ResponseSearchRecipe>() {


            //creamos metodo para cuando hay respuesta del servidor
            @Override
            public void onResponse(@NonNull Call<ResponseSearchRecipe> call, @NonNull Response<ResponseSearchRecipe> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(Search.this, "Conexión exitosa", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Search.this, "No se ha logrado conectar con el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseSearchRecipe> call, @NonNull Throwable t) {

            }

        });

        // añadimos click listener al botón
        btnSearch.setOnClickListener(this);


        View btnSearch = null;
        assert false;
        btnSearch.setOnClickListener(this);
    }


    private void events() {
    }private void findViews() {

    }

    private static final String[] MEDICINES = new String[] {
            "paracetamol", "valium", "apiretal", "zolpidem", "amoxicilina","lorazepam","diazepam","fentanilo","risperidona","citalopram"
    };

    private void getData(String patient, String date, String medication, String tomas) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_DOCTORPLUS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestSearchRecipe requestSearchRecipe = new RequestSearchRecipe(patient, date, medication, tomas );

        Call<ResponseSearchRecipe> call = doctorPlusAuthServices.getSearchRecipe("Bearer " + SharedPreferencesManager.getSomeStringValue(USER_TOKEN), requestSearchRecipe);


        call.enqueue(new Callback<ResponseSearchRecipe>() {
            @Override
            public void onResponse(@NonNull Call<ResponseSearchRecipe> call, @NonNull Response<ResponseSearchRecipe> response) {
                Toast.makeText(Search.this, "Datos recogidos de la API", Toast.LENGTH_SHORT).show();

                etPacientName.setText("");
                etDate.setText("");
                acMedicines.setText("");
                etNumberTomas.setText("");

            }

            @Override
            public void onFailure(@NonNull Call<ResponseSearchRecipe> call, @NonNull Throwable t) {
               Toast.makeText(Search.this, "Error al recuperar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    }










