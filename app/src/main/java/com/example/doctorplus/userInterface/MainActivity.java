package com.example.doctorplus.userInterface;

import static com.example.doctorplus.common.Constantes.ROLE_MEDIC;
import static com.example.doctorplus.common.Constantes.ROLE_OTHERS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorplus.common.Constantes;
import com.example.doctorplus.common.SharedPreferencesManager;
import com.example.doctorplus.R;
import com.example.doctorplus.retrofit.DoctorPlusClient;
import com.example.doctorplus.retrofit.DoctorPlusService;
import com.example.doctorplus.retrofit.request.RequestLogin;
import com.example.doctorplus.retrofit.response.ResponseAuth;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


    public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button btnLogin;
        EditText etNumberId, etPassword;
        DoctorPlusClient doctorPlusClient;
        DoctorPlusService doctorPlusService;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Objects.requireNonNull(getSupportActionBar()).hide();

            retrofitInit();
            findViews();
            events();

        }

        private void retrofitInit() {
            doctorPlusClient = DoctorPlusClient.getInstance();
            doctorPlusService = doctorPlusClient.getDoctorPlusService();
        }

        private void findViews() {
            btnLogin = findViewById(R.id.btnLogin);
            etNumberId = findViewById(R.id.editTextNumberId);
            etPassword = findViewById(R.id.editTextPassword);
        }

        private void events() {
            btnLogin.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.btnLogin) {
                goToLogin();
            }
        }

        private void goToLogin() {
            String id = etNumberId.getText().toString();
            String password = etPassword.getText().toString();
            SharedPreferencesManager.setSomeStringValue(Constantes.USER_ROLE, "");
            SharedPreferencesManager.setSomeStringValue(Constantes.USER_ID, "");
            SharedPreferencesManager.setSomeStringValue(Constantes.USER_NAME, "");
            SharedPreferencesManager.setSomeStringValue(Constantes.USER_TOKEN, "");

            if(id.isEmpty()) {
                etNumberId.setError("La Id es requerida");
            } else if(password.isEmpty()) {
                etPassword.setError("La contraseña es requerida");
            } else {
                RequestLogin requestLogin = new RequestLogin(id, password);

                Call<ResponseAuth> call = doctorPlusService.doLogin(requestLogin);

                call.enqueue(new Callback<ResponseAuth>() {

                    //creamos metodo para cuando hay respuesta del servidor
                    @Override
                    public void onResponse(@NonNull Call<ResponseAuth> call, @NonNull Response<ResponseAuth> response) {
                        if(response.isSuccessful()) {
                            SharedPreferencesManager.setSomeStringValue(Constantes.USER_ROLE, response.body().getRol());
                            SharedPreferencesManager.setSomeStringValue(Constantes.USER_ID, response.body().getUserId());
                            SharedPreferencesManager.setSomeStringValue(Constantes.USER_NAME, response.body().getNombre());
                            SharedPreferencesManager.setSomeStringValue(Constantes.USER_TOKEN, response.body().getAccessToken());

                            Toast.makeText(MainActivity.this, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show();

                            //Cuando hacemos login de manera exitosa nos lleva  otra activity, en este caso a la activity MedicUser
                            //Cómo nos llevará a OtherUsers?

                            assert response.body() != null;
                            if(ROLE_MEDIC.equals(response.body().getRol())){
                                Intent i = new Intent(MainActivity.this, MedicUser.class);
                                startActivity(i);

                                // Destruimos este Activity para que no se pueda volver.
                                finish();

                            } else if (ROLE_OTHERS.equals(response.body().getRol())) {
                                Intent i = new Intent(MainActivity.this, OtherUsers.class);
                                startActivity(i);
                                // Destruimos este Activity para que no se pueda volver.
                                finish();

                            } else {
                                Toast.makeText(MainActivity.this, "Error en rol usuario", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Algo fue mal, revise sus datos de acceso", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //el otro metodo es para cuando tenemos problemas de conexión con el servidor
                    @Override
                    public void onFailure(@NonNull Call<ResponseAuth> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "Problemas de conexión. Inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }

    }