package com.ydiworld.nucleus;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ydiworld.nucleus.databinding.ActivityPersonBinding;

/**
 * Created by sammy on 11/5/17.
 */

public class PersonFrag extends Fragment {

    private ActivityPersonBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.activity_person, container, false);
        View view = binding.getRoot();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preferencesKey), Context.MODE_PRIVATE);
        String fullname = sharedPref.getString(getString(R.string.full_name), "");
        String phone = sharedPref.getString(getString(R.string.phone_number), "");
        String email = sharedPref.getString(getString(R.string.email_address), "");
        String hear = sharedPref.getString(getString(R.string.hear_from), "");
        String career = sharedPref.getString(getString(R.string.career), "");
        String first = sharedPref.getString(getString(R.string.first_time), "");
        String gender = sharedPref.getString(getString(R.string.gender), "");
        String tribe = sharedPref.getString(getString(R.string.tribe), "");


        binding.name.setText(fullname);

        binding.age.setText(career + " - " + "(" + setTitleCase(gender) + ")");
        binding.yearjoined.setText(phone + "\n" + email + "\n" + "Tribe: " + tribe);

        binding.heardcamp.setText("How did you first hear about Camp Joseph: " + hear);
        binding.notfirst.setText("First time at Camp Joseph: " + first);
        //View view = inflater.inflate(R.layout.activity_person, container, false);

        /*TextView name = (TextView)view.findViewById(R.id.name);
        TextView age = (TextView)view.findViewById(R.id.age);
        TextView yearJoined = (TextView)view.findViewById(R.id.yearjoined);
        TextView occupation = (TextView)view.findViewById(R.id.occupation);
        TextView phone = (TextView)view.findViewById(R.id.phone);
        TextView email = (TextView)view.findViewById(R.id.email);
        TextView heardcamp = (TextView)view.findViewById(R.id.heardcamp);
        TextView notFirst = (TextView)view.findViewById(R.id.notfirst);
        Button share = (Button)view.findViewById(R.id.sharebtn);*/
        return view;
    }

    private String setTitleCase(String input){
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        String titleCaseValue = sb.toString();

        return titleCaseValue;
    }
}
