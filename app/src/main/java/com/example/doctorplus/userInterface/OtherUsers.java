package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.USER_ID;
import static com.example.doctorplus.common.Constantes.USER_NAME;
import static com.example.doctorplus.common.Constantes.USER_ROLE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctorplus.R;
import com.example.doctorplus.common.SharedPreferencesManager;

public class OtherUsers extends AppCompatActivity implements View.OnClickListener{
   TextView tvOthersRole, tvOthersId, tvOthersName;
   Button btnSearchR;


    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_users);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tvOthersRole = findViewById(R.id.textViewOthersRole);
        tvOthersId = findViewById(R.id.textViewOthersId);
        tvOthersName = findViewById(R.id.textViewOthersName);


        tvOthersRole.setText(SharedPreferencesManager.getSomeStringValue(USER_ROLE));
        tvOthersId.setText(SharedPreferencesManager.getSomeStringValue(USER_ID));
        tvOthersName.setText(SharedPreferencesManager.getSomeStringValue(USER_NAME));

        findViews();
        events();

    }
    private void events() {
        btnSearchR.setOnClickListener(this);
    }

    private void findViews() {
        btnSearchR = findViewById(R.id.buttonSearchR);
    }


    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonSearchR) {
            goToSearch();
        }
    }

    private void goToSearch() {
        Intent i = new Intent(OtherUsers.this, Search.class);
        startActivity(i);

        // Destruimos este Activity para que no se pueda volver.
        finish();
    }
}