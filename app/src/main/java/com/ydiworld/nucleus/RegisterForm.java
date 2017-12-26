package com.ydiworld.nucleus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.inputmethod.InputMethodManager;
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
    String fullName, userPhone, userEmail, userHear, userCareer, userFirst, userGender;
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

        /*regUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/



        setThingsUp();



        binding.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullName = binding.fullname.getText().toString();
                userPhone = binding.phone.getText().toString();
                userEmail = binding.email.getText().toString();
                userHear = binding.hear.getText().toString();
                userCareer = binding.career.getText().toString();
                userFirst = binding.first.getText().toString();
                userGender = binding.gender.getText().toString();

                if (fullName.equals("")){
                    showBasicDialog("Field empty" + fullName, "You haven't filled out your name yet.", "Okay");
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


                    hideSoftKeyboard(RegisterForm.this);
                    binding.content.setVisibility(View.GONE);
                    binding.loader.setVisibility(View.VISIBLE);
                    connectApi(BASE_URL, fullName, userPhone, userEmail, userHear, userCareer, userFirst, userGender);
                }
            }
        });

    }

    private void showBasicDialog(String title, String content, String agree){
        new MaterialDialog.Builder(this)
                .title(title)
                .content(content)
                .positiveText(agree)
                .show();
    }

    Unregistrar mUnregistrar;

    public void hideSoftKeyboard(Activity activity) {
        //InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.fullname.getWindowToken(), 0);
    }

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


        binding.fullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
                    setWinFlags();
                }
            }
        });

        binding.phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
                    setWinFlags();
                }
            }
        });

        binding.email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
                    setWinFlags();
                }
            }
        });

        binding.hear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
                    setWinFlags();
                }
            }
        });

        binding.career.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
                    setWinFlags();
                }
            }
        });

        binding.first.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
                    setWinFlags();
                }
            }
        });

        binding.gender.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    clearWinFlags();
                } else {
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


    private void connectApi(String base_url, String fullname, final String userPhone, final String userEmail, final String userHear, String usercareer, final String userFirst, final String userGender) {
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

                    // we are checking for case where someone has already registered.
                    // We won't allow the same registration happen again
                    if (response.body().getStatus()){

                        //Log.e("X", response.body().getStatus().toString());
                        /*String fullname = response.body().getParticipant().getFullname();
                        String phone = response.body().getParticipant().getPhone();
                        String email = response.body().getParticipant().getEmail();
                        String hear = response.body().getParticipant().getHearAboutCamp();
                        String career = response.body().getParticipant().getCareer();
                        String first = response.body().getParticipant().getFirstTimeAtCamp();
                        String gender = response.body().getParticipant().getGender();
                        lunchActivity(fullname,phone,email,hear,career,first,gender);*/

                        String fullname = fullName;
                        String phone = userPhone;
                        String email = userEmail;
                        String hear = userHear;
                        String career = userCareer;
                        String first = userFirst;
                        String gender = userGender;

                        String tribe = response.body().getTribe();
                        String parti_id = response.body().getID();


                        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencesKey), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString(getString(R.string.full_name), fullname).commit();
                        editor.putString(getString(R.string.phone_number), phone).commit();
                        editor.putString(getString(R.string.email_address), email).commit();
                        editor.putString(getString(R.string.hear_from), hear).commit();
                        editor.putString(getString(R.string.career), career).commit();
                        editor.putString(getString(R.string.first_time), first).commit();
                        editor.putString(getString(R.string.gender), gender).commit();
                        editor.putString(getString(R.string.tribe), tribe).commit();
                        editor.putString(getString(R.string.last_id), parti_id).commit();
                        editor.commit();

                        launchActivity(fullname, phone,email,hear,career, first, gender, tribe);

                    } else {

                        //email with this registration exists
                        if (response.body().getReason().equals("exists")){

                            showBasicDialog("Account exists", "This email has already been registered for this event. Sign in with this email or use another email.", "Okay");
                            binding.content.setVisibility(View.VISIBLE);
                            binding.loader.setVisibility(View.GONE);
                        }


                    }

                }
            }

            @Override
            public void onFailure(Call<NewUser> call, Throwable t) {
                showBasicDialog("Error", "Sorry, an error occured. Please try again", "Okay");
            }
        });
    }

    private void launchActivity(String name, String phone, String email, String hear, String career, String first, String gender, String tribe){
        Intent intent = new Intent(this, MainActivity.class);
        /*Bundle bundle = new Bundle();
        bundle.putString("fullname", name);
        bundle.putString("phone", phone);
        bundle.putString("email", email);
        bundle.putString("hear", hear);
        bundle.putString("career", career);
        bundle.putString("first", first);
        bundle.putString("gender", gender);
        bundle.putString("tribe", tribe);
        intent.putExtra("userDetail", bundle);*/
        startActivity(intent);
    }
}
