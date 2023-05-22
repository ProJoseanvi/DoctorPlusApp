package com.example.doctorplus.userInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doctorplus.R;

public class ResultsSearchOtherUsers extends AppCompatActivity {

    private ImageView ivPantallaAnterior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_search_other_users);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ivPantallaAnterior = findViewById(R.id.imageViewLogoReverse2);
        
        findViews();

        ivPantallaAnterior.setOnClickListener(this::onClick);


        ImageView imageViewLogoCreate = findViewById(R.id.imageViewLogoReverse2);
        imageViewLogoCreate.setOnClickListener(v -> {
            Intent intent = new Intent(ResultsSearchOtherUsers.this, OtherUsers.class);
            startActivity(intent);
        });
        
    }

    private void onClick(View view) {
    }

    private void findViews() {
    }
}
   