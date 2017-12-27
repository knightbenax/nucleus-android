package com.ydiworld.nucleus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sammy on 11/4/17.
 */

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    Fragment personFrag = new PersonFrag();
    Bundle bundle;

    private final String BASE_URL = "http://campjoseph.ydiworld.org/";
    private Retrofit retrofit = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parent);

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainArea, personFrag);
        fragmentTransaction.commit();


        setThingsUp();
    }


    private void setThingsUp(){
        setWinFlags();

        ImageView location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        ImageView prog = findViewById(R.id.calendar);

        prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        ImageView speakers = findViewById(R.id.speakers);

        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpeakersActivity.class);
                startActivity(intent);
            }
        });

        ImageView details = findViewById(R.id.details);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OfficialsActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.back_bg);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencesKey), Context.MODE_PRIVATE);
        String Uri = sharedPref.getString(getString(R.string.avatar), "");

        Picasso.with(this).load(Uri).into(imageView);
        //Log.e("Any","outside");


        connectAndGetUpdatedData(BASE_URL);

    }


    private void saveArraylist(List<?> collection, String KEY){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preferencesKey), 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        Gson gson = new Gson();
        String arrayList1 = gson.toJson(collection);
        prefEditor.putString(KEY, arrayList1.toString());
        prefEditor.commit();
    }


    private void connectAndGetUpdatedData(String base_url) {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        NucleusInterface nucleusInterface = retrofit.create(NucleusInterface.class);
        nucleusInterface.getUpdatedData().enqueue(new Callback<UpdatedEventData>() {
            @Override
            public void onResponse(Call<UpdatedEventData> call, Response<UpdatedEventData> response) {
                if (response.isSuccessful()){


                    if (response.body().getStatus()){
                        //lunchActivity();

                        List<Event> thisevent = response.body().getEvents();

                        List<Official> officials = response.body().getOfficials();

                        saveArraylist(thisevent, "events");
                        saveArraylist(officials, "officials");

                    }
                }
            }

            @Override
            public void onFailure(Call<UpdatedEventData> call, Throwable t) {

            }
        });
    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
}
