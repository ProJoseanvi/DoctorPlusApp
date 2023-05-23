package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.USER_ID;
import static com.example.doctorplus.common.Constantes.USER_NAME;
import static com.example.doctorplus.common.Constantes.USER_ROLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;

import java.util.Objects;

public class MedicUser extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medic_user);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView textViewRole = findViewById(R.id.textViewRole);
        TextView textViewId = findViewById(R.id.textViewId);
        TextView textViewNombre = findViewById(R.id.textViewNombre);

        textViewRole.setText(SharedPreferencesManager.getSomeStringValue(USER_ROLE));
        textViewId.setText(SharedPreferencesManager.getSomeStringValue(USER_ID));
        textViewNombre.setText(SharedPreferencesManager.getSomeStringValue(USER_NAME));

        Button buttonCreaReceta = findViewById(R.id.buttonCreaReceta);
        buttonCreaReceta.setOnClickListener(this);

        Button buttonBuscarReceta = findViewById(R.id.buttonBuscarReceta);
        buttonBuscarReceta.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonCreaReceta) {
            goToCreateReceta();
        }

        if (v.getId() == R.id.buttonBuscarReceta) {
            goToBuscarReceta();
        } else v.getId();
    }

    private void goToCreateReceta() {
        Intent intent = new Intent(this, Create.class);
        startActivity(intent);
        finish();
    }

    private void goToBuscarReceta() {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
        finish();
    }

}


