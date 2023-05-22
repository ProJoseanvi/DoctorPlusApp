package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.DATE;
import static com.example.doctorplus.common.Constantes.MEDICATION;
import static com.example.doctorplus.common.Constantes.NUMBER_TOMAS;
import static com.example.doctorplus.common.Constantes.NUMERO_ASIGNADO;
import static com.example.doctorplus.common.Constantes.PATIENT_NAME;
import static com.example.doctorplus.common.Constantes.USER_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;

import java.util.Objects;

public class ResultsSearchMedic extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_search_medic);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView textViewRecipe = findViewById(R.id.textViewRecipe);
        TextView textViewNumberUser = findViewById(R.id.textViewNumbUser);
        TextView textViewPacient = findViewById(R.id.textViewPacient);
        TextView textViewDate = findViewById(R.id.textViewDate);
        TextView textViewMeds = findViewById(R.id.textViewMeds);
        TextView textViewNumberTomas = findViewById(R.id.textViewNumberTomas);
        ImageView ivLogoReverse = findViewById(R.id.imageViewLogoReverse);

        textViewRecipe.setText(SharedPreferencesManager.getSomeStringValue(NUMERO_ASIGNADO));
        textViewNumberUser.setText(SharedPreferencesManager.getSomeStringValue(USER_ID));
        textViewPacient.setText(SharedPreferencesManager.getSomeStringValue(PATIENT_NAME));
        textViewDate.setText(SharedPreferencesManager.getSomeStringValue(DATE));
        textViewMeds.setText(SharedPreferencesManager.getSomeStringValue(MEDICATION));
        textViewNumberTomas.setText(SharedPreferencesManager.getSomeStringValue(NUMBER_TOMAS));

        Button buttonNewSearch = findViewById(R.id.buttonNewSearch);
        buttonNewSearch.setOnClickListener(this);

        Button buttonDelReceta = findViewById(R.id.buttonDelReceta);
        buttonDelReceta.setOnClickListener(this);

        Button buttonModificar = findViewById(R.id.buttonModificar);
        buttonModificar.setOnClickListener(this);


        ivLogoReverse.setOnClickListener(this::onClick);


        ImageView imageViewLogoCreate = findViewById(R.id.imageViewLogoReverse);
        imageViewLogoCreate.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsSearchMedic.this, MedicUser.class);
            startActivity(intent);
        });

    }

    public void onClick (View v){
        if (v.getId() == R.id.buttonNewSearch) {
            goToSearch();
        }

    }

    private void goToSearch () {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
        finish();
    }

}

