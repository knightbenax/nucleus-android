package com.ydiworld.nucleus;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ydiworld.nucleus.databinding.ActivitySignInFormBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Bezaleel Ashefor.
 */

public class SignInForm extends AppCompatActivity {

    EditText email;
    Button regUserBtn;
    private final String BASE_URL = "http://campjoseph.ydiworld.org/";
    private Retrofit retrofit = null;

    private ActivitySignInFormBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in_form);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in_form);

        email = findViewById(R.id.email);
        //regUserBtn = findViewById(R.id.registerbtn);

        binding.signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                connectApi(BASE_URL, userEmail);
            }
        });

        setThingsUp();

    }

    private void setThingsUp(){

        setWinFlags();

        if (hasNavBar(getResources())){
            callThePolice();
        }



    }


    public void callThePolice(){


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                setWinFlags();

                int top_padding = getStatusBar();
                int bottom_padding = getNavBar();
                int request_padding = getResources().getDimensionPixelSize(R.dimen.request_padding);
                bottom_padding = request_padding + bottom_padding;
                RelativeLayout.LayoutParams welcome_newHeight = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                binding.mainPanel.setLayoutParams(welcome_newHeight);
                //RelativeLayout.LayoutParams bottom_rel = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //bottom_rel.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                //binding.mainPanel.setLayoutParams(bottom_rel);

                if (hasNavBar(getResources()) == true){
                    //means we need to make space for the navbar
                    //mainPanel.setPadding(0, top_padding, 0, bottom_padding);
                    binding.mainPanel.setPadding(0, top_padding, 0, bottom_padding);
                    //binding.mainPanel.setPadding(0, 0, 0, bottom_padding);
                } else {
                    binding.mainPanel.setPadding(0, 0, 0, 0);
                    //mainPanel.setPadding(0, top_padding, 0, 0);
                }

           /* mainPanel.setLayoutParams(welcome_newHeight);

            mainPanel.setPadding(0, top_padding, 0, 0);



            if(hasNavBar(getResources()) == true){

            } else {

            }*/


            }
    }

    public int getStatusBar(){
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public int getNavBar(){
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    public boolean hasNavBar (Resources resources)
    {
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

    public void clearWinFlags(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void setWinFlags(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void connectApi(String base_url, String userEmail) {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        NucleusInterface nucleusInterface = retrofit.create(NucleusInterface.class);
        nucleusInterface.siginInUser(userEmail).enqueue(new Callback<ExistingUser>() {
            @Override
            public void onResponse(Call<ExistingUser> call, Response<ExistingUser> response) {
                if (response.isSuccessful()){
                    lunchActivity();
                }
            }

            @Override
            public void onFailure(Call<ExistingUser> call, Throwable t) {

            }
        });
    }

    private void lunchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
