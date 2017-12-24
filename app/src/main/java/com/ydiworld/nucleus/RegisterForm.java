package com.ydiworld.nucleus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sammy on 12/24/17.
 */

public class RegisterForm extends AppCompatActivity {

    EditText name,phone,email,hear,career,first,gender;
    Button regUserBtn;
    private final String BASE_URL = "http://campjoseph.ydiworld.org/";
    private Retrofit retrofit = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_form);

        name = findViewById(R.id.fullname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        hear = findViewById(R.id.hear);
        career = findViewById(R.id.career);
        first = findViewById(R.id.first);
        gender = findViewById(R.id.gender);
        regUserBtn = findViewById(R.id.registerbtn);



        regUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = name.getText().toString();
                String userPhone = phone.getText().toString();
                String userEmail = email.getText().toString();
                String userHear = hear.getText().toString();
                String userCareer = career.getText().toString();
                String userFirst = first.getText().toString();
                String userGender = gender.getText().toString();

                connectApi(BASE_URL, fullName, userPhone, userEmail, userHear, userCareer, userFirst, userGender);


            }
        });

    }

    private void connectApi(String base_url, String fullname, String userPhone, String userEmail, String userHear, String usercareer, String userFirst, String userGender) {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        NucleusInterface nucleusInterface = retrofit.create(NucleusInterface.class);
        nucleusInterface.createUser(fullname,userPhone,userEmail,userHear,usercareer,userFirst, userGender).enqueue(new Callback<NewUser>() {
            @Override
            public void onResponse(Call<NewUser> call, Response<NewUser> response) {
                if (response.isSuccessful()){
                    lunchActivity();
                }
            }

            @Override
            public void onFailure(Call<NewUser> call, Throwable t) {

            }
        });
    }

    private void lunchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
