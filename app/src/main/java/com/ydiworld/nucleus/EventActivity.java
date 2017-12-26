package com.ydiworld.nucleus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydiworld.nucleus.databinding.ActivityEventBinding;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sammy on 11/4/17.
 */

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);

        setThingsUp();
    }


    private void saveArraylist(List<Event> collection, String KEY){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preferencesKey), 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        Gson gson = new Gson();
        String arrayList1 = gson.toJson(collection);
        prefEditor.putString(KEY, arrayList1.toString());
        prefEditor.commit();
    }

    private List<Event> getArrayList(String KEY){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preferencesKey), 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        //String storedCollection = pref.getString(KEY, null);

        List<Event> collection = new ArrayList<Event>();
        Gson gson = new Gson();
        String arrayListString = pref.getString(KEY, null);
        Type type = new TypeToken<List<Event>>() {}.getType();
        collection = gson.fromJson(arrayListString, type);

        return collection;
    }

    EventAdapter thisAdapter;
    List<Event> this_events;

    private void setThingsUp(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event);

        setWinFlags();

        ImageView location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        ImageView person = findViewById(R.id.person);

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView speakers = findViewById(R.id.speakers);

        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, SpeakersActivity.class);
                startActivity(intent);
            }
        });

        ImageView details = findViewById(R.id.details);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, OfficialsActivity.class);
                startActivity(intent);
            }
        });


        this_events = getArrayList("events");
        thisAdapter = new EventAdapter();

        binding.eventRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // Do not draw the divider
            }
        });

        binding.eventRecycler.setAdapter(thisAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.eventRecycler.setLayoutManager(mLayoutManager);
        binding.eventRecycler.setHasFixedSize(true);


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        if (formattedDate.equals("27-Dec-2017")){
            thisAdapter.eventList = this_events.get(0).getContent();
            thisAdapter.notifyDataSetChanged();

            binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding);
            binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
        } else if (formattedDate.equals("28-Dec-2017")){
            thisAdapter.eventList = this_events.get(1).getContent();
            thisAdapter.notifyDataSetChanged();

            binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.secdate.setBackgroundResource(R.drawable.green_bg_padding);
            binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
        } else if (formattedDate.equals("29-Dec-2017")){
            thisAdapter.eventList = this_events.get(2).getContent();
            thisAdapter.notifyDataSetChanged();

            binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding);
            binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
        } else if(formattedDate.equals("30-Dec-2017")) {
            thisAdapter.eventList = this_events.get(3).getContent();
            thisAdapter.notifyDataSetChanged();

            binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding);
        } else {

            thisAdapter.eventList = this_events.get(0).getContent();
            thisAdapter.notifyDataSetChanged();

            binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding);
            binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
        }



        binding.firstdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisAdapter.eventList = this_events.get(0).getContent();
                thisAdapter.notifyDataSetChanged();
                binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding);
                binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            }
        });


        binding.secdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisAdapter.eventList = this_events.get(1).getContent();
                thisAdapter.notifyDataSetChanged();
                binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.secdate.setBackgroundResource(R.drawable.green_bg_padding);
                binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            }
        });


        binding.thirddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisAdapter.eventList = this_events.get(2).getContent();
                thisAdapter.notifyDataSetChanged();
                binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding);
                binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
            }
        });

        binding.fourthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisAdapter.eventList = this_events.get(3).getContent();
                thisAdapter.notifyDataSetChanged();
                binding.firstdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.secdate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.thirddate.setBackgroundResource(R.drawable.green_bg_padding_trans);
                binding.fourthdate.setBackgroundResource(R.drawable.green_bg_padding);
            }
        });


        //Log.e("X", String.valueOf(this_events.size()));

        //Log.e("X", String.valueOf(thisAdapter.getItemCount()));

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
