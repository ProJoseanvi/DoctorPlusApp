package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.USER_ID;
import static com.example.doctorplus.common.Constantes.USER_NAME;
import static com.example.doctorplus.common.Constantes.USER_ROLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;

public class MedicUser extends AppCompatActivity implements View.OnClickListener{
   TextView tvRole, tvId, tvNombre;
    Button btnCreaReceta;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medic_user);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tvRole = findViewById(R.id.textViewRole);
        tvId = findViewById(R.id.textViewId);
        tvNombre = findViewById(R.id.textViewNombre);


        tvRole.setText(SharedPreferencesManager.getSomeStringValue(USER_ROLE));
        tvId.setText(SharedPreferencesManager.getSomeStringValue(USER_ID));
        tvNombre.setText(SharedPreferencesManager.getSomeStringValue(USER_NAME));

        findViews();
        events();
    }

    private void events() {
        btnCreaReceta.setOnClickListener(this);
    }

    private void findViews() {
        btnCreaReceta = findViewById(R.id.buttonCreaReceta);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonCreaReceta) {
            goToCreateReceta();
        }
    }

    private void goToCreateReceta() {
        Intent i = new Intent(MedicUser.this, Create.class);
        startActivity(i);

        // Destruimos este Activity para que no se pueda volver.
        finish();
    }
}




