package com.ydiworld.nucleus;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ydiworld.nucleus.databinding.ActivitySpeakersBinding;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SpeakersActivity extends AppCompatActivity {

    private ActivitySpeakersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);

        setThingsUp();
    }

    private void setThingsUp(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_speakers);

        setWinFlags();

        ImageView location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakersActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        ImageView person = findViewById(R.id.person);

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        ImageView prog = findViewById(R.id.calendar);

        prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakersActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        ImageView details = findViewById(R.id.details);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeakersActivity.this, OfficialsActivity.class);
                startActivity(intent);
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
