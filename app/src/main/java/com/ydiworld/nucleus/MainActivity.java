package com.ydiworld.nucleus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sammy on 11/4/17.
 */

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    Fragment personFrag = new PersonFrag();
    Fragment eventFrag = new EventFrag();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parent);


//        Fragment sideFrag = new SideBarFrag();

        fragmentTransaction = getFragmentManager().beginTransaction();

        Bundle bundle = getIntent().getBundleExtra("userDetail");
        String fromScreen = bundle.getString("Activity");

//        fragmentTransaction.add(R.id.mainArea, personFrag).commit();

        changeScreens(fromScreen);
    }

    private void changeScreens(String screen){

        switch (screen){
            case "person":
            {
                fragmentTransaction.replace(R.id.mainArea, personFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            case "event":
            {
                fragmentTransaction.replace(R.id.mainArea, eventFrag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }

    }
}
