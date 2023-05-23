package com.example.doctorplus.userInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.R;


public class Search extends AppCompatActivity implements View.OnClickListener {

    Button btnSearch;
    AutoCompleteTextView acNumberRecipe, acPatient, acDate ;

    ImageView ivPantallaRewind;


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


        ImageView imageViewLogoRewind = findViewById(R.id.imageViewLogoRewind);
        imageViewLogoRewind.setOnClickListener(v -> {
            Intent intent = new Intent(Search.this, MedicUser.class);
            startActivity(intent);
        });

    }


    @Override
    public void onClick(View v) {

    }
}






