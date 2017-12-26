package com.ydiworld.nucleus;

import android.app.Application;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

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

        SessionConfiguration config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("BMWIDT4qmsm5qd5rU819g4TgEwSmQ_-u")
                // required for enhanced button features
                .setServerToken("qLrv9tDLtLSNccHF2UgtMUFojSe2l-XgTM1TdhBM")
                // required for implicit grant authentication
                .setRedirectUri("http://campjoseph.ydiworld.org")
                // required scope for Ride Request Widget features
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                // optional: set sandbox as operating environment
                //.setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();

        UberSdk.initialize(config);


    }
}
