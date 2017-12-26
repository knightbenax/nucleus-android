package com.ydiworld.nucleus;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydiworld.nucleus.databinding.ActivityEventBinding;
import com.ydiworld.nucleus.databinding.ActivityOfficialsBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OfficialsActivity extends AppCompatActivity {

    ActivityOfficialsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officials);

        setThingsUp();
    }

    private void setThingsUp(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_officials);

        setWinFlags();

        List<Official> this_officials = getArrayList("officials");

        Log.e("X", String.valueOf(this_officials.size()));
        Log.e("X", String.valueOf(this_officials.get(0)));
        Log.e("X", String.valueOf(this_officials.get(0).getBody()));
        //binding.contentText.setText(this_officials.get(0).getBody() + this_officials.get(1).getTitle() + this_officials.get(1).getBody() + this_officials.get(2).getTitle() + this_officials.get(2).getBody());

    }

    private List<Official> getArrayList(String KEY){
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preferencesKey), 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        //String storedCollection = pref.getString(KEY, null);

        List<Official> collection = new ArrayList<Official>();
        Gson gson = new Gson();
        String arrayListString = pref.getString(KEY, null);
        Type type = new TypeToken<List<?>>() {}.getType();
        collection = gson.fromJson(arrayListString, type);

        return collection;
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
