package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.USER_ID;
import static com.example.doctorplus.common.Constantes.USER_NAME;
import static com.example.doctorplus.common.Constantes.USER_ROLE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.doctorplus.R;

public class OtherUsers extends AppCompatActivity {
   TextView tvOthersRole, tvOthersId, tvOthersName;


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


        tvOthersRole.setText("User Role: " + USER_ROLE);
        tvOthersId.setText("User ID: " + USER_ID);
        tvOthersName.setText("User Name: " + USER_NAME);

    }
}