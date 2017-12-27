package com.ydiworld.nucleus;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.ydiworld.nucleus.databinding.ActivityPersonBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sammy on 11/5/17.
 */

public class PersonFrag extends Fragment {

    private ActivityPersonBinding binding;
    View view;

    private final String BASE_URL = "http://campjoseph.ydiworld.org/";
    private Retrofit retrofit = null;
    String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.activity_person, container, false);
        view = binding.getRoot();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preferencesKey), Context.MODE_PRIVATE);
        String fullname = sharedPref.getString(getString(R.string.full_name), "");
        String phone = sharedPref.getString(getString(R.string.phone_number), "");
        String email = sharedPref.getString(getString(R.string.email_address), "");
        String hear = sharedPref.getString(getString(R.string.hear_from), "");
        String career = sharedPref.getString(getString(R.string.career), "");
        String first = sharedPref.getString(getString(R.string.first_time), "");
        String gender = sharedPref.getString(getString(R.string.gender), "");
        String tribe = sharedPref.getString(getString(R.string.tribe), "");
        id = sharedPref.getString(getString(R.string.last_id), "");


        binding.name.setText(fullname);

        binding.age.setText(career + " - " + "(" + setTitleCase(gender) + ")");
        binding.yearjoined.setText(phone + "\n" + email + "\n" + "Tribe: " + tribe);

        binding.heardcamp.setText("How did you first hear about Camp Joseph: " + hear);
        binding.notfirst.setText("First time at Camp Joseph: " + first);
        setThingsUp();
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

    MaterialDialog progressDialog;

    public void checkInUser(){
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title("Check In?")
                .content("You are about to check in and mark yourself as arrived at Camp Joseph 2017.  After this you can proceed to collect your Welcome Pack.")
                .positiveText("Okay")
                .negativeText("Not Yet")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();

                        progressDialog = new MaterialDialog.Builder(getActivity())
                                .title("Checking you in")
                                .content("Please wait")
                                .cancelable(false)
                                .progress(true, 0)
                                .show();

                        markAsArrived(BASE_URL, id);
                    }
                })
                .show();
    }

    private void markAsArrived(String base_url, String id) {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        NucleusInterface nucleusInterface = retrofit.create(NucleusInterface.class);
        nucleusInterface.markAsArrived(id).enqueue(new Callback<Arrived>() {
            @Override
            public void onResponse(Call<Arrived> call, Response<Arrived> response) {
                if (response.isSuccessful()){

                    if (response.body().getStatus()){
                        //lunchActivity();

                        progressDialog.dismiss();

                        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preferencesKey), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putString(getString(R.string.arrival), "Yes").apply();
                        editor.commit();

                        Button check_in = view.findViewById(R.id.check_in_button);
                        check_in.setVisibility(View.GONE);
                        ImageView ticket = view.findViewById(R.id.ticket);
                        Picasso.with(getActivity()).load(R.drawable.ticket_admitted).into(ticket);

                    }
                }
            }

            @Override
            public void onFailure(Call<Arrived> call, Throwable t) {

                progressDialog.dismiss();


            }
        });
    }



    private void setThingsUp(){

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        if (formattedDate.equals("27-Dec-2017") || formattedDate.equals("28-Dec-2017") || formattedDate.equals("29-Dec-2017") || formattedDate.equals("30-Dec-2017")){

            SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preferencesKey), Context.MODE_PRIVATE);
            String savedArrival = sharedPref.getString(getString(R.string.arrival), "");

            if(savedArrival.equals("")){
                //user has arrived and checkedIn
                Button check_in = view.findViewById(R.id.check_in_button);
                check_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkInUser();
                    }
                });
                check_in.setVisibility(View.VISIBLE);
                ImageView ticket = view.findViewById(R.id.ticket);
                Picasso.with(getActivity()).load(R.drawable.ticket).into(ticket);

            } else {

                Button check_in = view.findViewById(R.id.check_in_button);
                check_in.setVisibility(View.GONE);
                check_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkInUser();
                    }
                });
                ImageView ticket = view.findViewById(R.id.ticket);
                Picasso.with(getActivity()).load(R.drawable.ticket_admitted).into(ticket);

            }

        } else {

            Button check_in = view.findViewById(R.id.check_in_button);
            check_in.setVisibility(View.GONE);
            check_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkInUser();
                }
            });
            ImageView ticket = view.findViewById(R.id.ticket);
            Picasso.with(getActivity()).load(R.drawable.ticket).into(ticket);
        }
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
