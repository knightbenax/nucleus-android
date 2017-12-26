package com.ydiworld.nucleus;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sammy on 11/4/17.
 */

public class SideBarFrag extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.side_btns, container, false);

        ImageView personImg = (ImageView)view.findViewById(R.id.person);
        ImageView locationImg = (ImageView)view.findViewById(R.id.location);
        ImageView calendarImg = (ImageView)view.findViewById(R.id.calendar);

        /*personImg.setImageResource(R.drawable.man);
        personImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        locationImg.setImageResource(R.drawable.placeholder);
        calendarImg.setImageResource(R.drawable.calendar);*/


        return view;
    }
}
