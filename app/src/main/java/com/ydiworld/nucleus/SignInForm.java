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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydiworld.nucleus.databinding.ActivitySignInFormBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                String userEmail = binding.email.getText().toString();

                if (userEmail.equals("")){
                    showBasicDialog("Field empty", "You haven't filled out your email yet.", "Okay");
                } else {

                    hideSoftKeyboard(SignInForm.this);
                    binding.content.setVisibility(View.GONE);
                    binding.loader.setVisibility(View.VISIBLE);
                    connectApi(BASE_URL, userEmail);
                }

            }
        });

        setThingsUp();

    }

    public void hideSoftKeyboard(Activity activity) {
        //InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.email.getWindowToken(), 0);
    }

    private void setThingsUp(){

        setWinFlags();

        if (hasNavBar(getResources())){
            callThePolice();
        }

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


    private void showBasicDialog(String title, String content, String agree){
        new MaterialDialog.Builder(this)
                .title(title)
                .content(content)
                .positiveText(agree)
                .show();
    }

    private void saveArraylist(List<?> collection, String KEY){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preferencesKey), 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        Gson gson = new Gson();
        String arrayList1 = gson.toJson(collection);
        prefEditor.putString(KEY, arrayList1.toString());
        prefEditor.commit();
    }

    private List<?> getArrayList(String KEY){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preferencesKey), 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        //String storedCollection = pref.getString(KEY, null);

        List<?> collection = new ArrayList<>();
        Gson gson = new Gson();
        String arrayListString = pref.getString(KEY, null);
        Type type = new TypeToken<List<?>>() {}.getType();
        collection = gson.fromJson(arrayListString, type);

        return collection;
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


                    if (response.body().getStatus()){
                        //lunchActivity();
                        String fullname = response.body().getParticipant().getFullname();
                        String phone = response.body().getParticipant().getPhone();
                        String email = response.body().getParticipant().getEmail();
                        String hear = response.body().getParticipant().getHearAboutCamp();
                        String career = response.body().getParticipant().getCareer();
                        String first = response.body().getParticipant().getFirstTimeAtCamp();
                        String gender = response.body().getParticipant().getGender();
                        String tribe = response.body().getParticipant().getTribe();
                        String parti_id = response.body().getParticipant().getID().toString();

                        List<Event> thisevent = response.body().getEvents();

                        List<Official> officials = response.body().getOfficials();

                        saveArraylist(thisevent, "events");
                        saveArraylist(officials, "officials");

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

                        Intent intent = new Intent(SignInForm.this, ChoosePhotoActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        if (response.body().getReason().equals("no exist")){
                            binding.content.setVisibility(View.VISIBLE);
                            binding.loader.setVisibility(View.GONE);
                            showBasicDialog("Account not found", "No registration data was found with this email.", "Okay");
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ExistingUser> call, Throwable t) {
                showBasicDialog("Error", "Sorry, an error occured. Please try again", "Okay");
            }
        });
    }

    private void lunchActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
