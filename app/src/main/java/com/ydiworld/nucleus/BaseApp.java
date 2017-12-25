package com.ydiworld.nucleus;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by knightbenax on 22/02/2017.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Cabin/Cabin-Regular-TTF.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }
}
