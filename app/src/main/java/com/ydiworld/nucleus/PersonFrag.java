package com.tamu.sam.lavet.mest.campjoseph;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sammy on 11/5/17.
 */

public class PersonFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_person, container, false);

        TextView name = (TextView)view.findViewById(R.id.name);
        TextView age = (TextView)view.findViewById(R.id.age);
        TextView yearJoined = (TextView)view.findViewById(R.id.yearjoined);
        TextView occupation = (TextView)view.findViewById(R.id.occupation);
        TextView phone = (TextView)view.findViewById(R.id.phone);
        TextView email = (TextView)view.findViewById(R.id.email);
        TextView heardcamp = (TextView)view.findViewById(R.id.heardcamp);
        TextView notFirst = (TextView)view.findViewById(R.id.notfirst);
        Button share = (Button)view.findViewById(R.id.sharebtn);

        return view;
    }
}
