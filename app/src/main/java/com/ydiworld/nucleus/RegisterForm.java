package com.ydiworld.nucleus;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ydiworld.nucleus.databinding.ActivityRegisterFormBinding;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sammy on 12/24/17.
 */

public class RegisterForm extends AppCompatActivity {

    EditText name,phone,email,hear,career,first,gender;
    Button regUserBtn;
    private final String BASE_URL = "http://campjoseph.ydiworld.org/";
    private Retrofit retrofit = null;


    private ActivityRegisterFormBinding binding;

    // we start with the keyboard hidden. We can to trick the activity
    // to set the flags back on when we press back. Because Android is a bastard.
    // TEMP: Didn't use again
    private boolean keyboardVisible = false;


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

                if (fullName.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out your name yet.", "Okay");
                } else if (userPhone.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out your phone number.", "Okay");
                } else if (userEmail.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out your email yet.", "Okay");
                } else if(userHear.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out how you heard about Camp Joseph.", "Okay");
                } else if (userCareer.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out what you do for a living.", "Okay");
                } else if (userFirst.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out if this is your first time.", "Okay");
                } else if (userGender.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out your gender yet. Hope all is well?", "Okay");
                } else {

                    connectApi(BASE_URL, fullName, userPhone, userEmail, userHear, userCareer, userFirst, userGender);
                }
            }
        });



        setThingsUp();

    }

    private void showBasicDialog(String title, String content, String agree){
        new MaterialDialog.Builder(this)
                .title(title)
                .content(content)
                .positiveText(agree)
                .show();
    }

    Unregistrar mUnregistrar;

    private void setThingsUp(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_form);

        setWinFlags();

        if (hasNavBar(getResources())){
            callThePolice();
        }

        //hack to set back the windowFlags on keyboard hide
        mUnregistrar = KeyboardVisibilityEvent.registerEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if(!isOpen){
                    setWinFlags();
                }
            }
        });


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                    Log.e("X", response.toString());
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
