package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface NucleusInterface {
    @POST("/api/register/new")
    @FormUrlEncoded
    Call<NewUser> createUser(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("hear") String hear,
            @Field("career") String career,
            @Field("first") String first,
            @Field("gender") String gender
    );


    @POST("/api/register/participant")
    @FormUrlEncoded
    Call<NewUser> siginInUser(
            @Field("email") String email
    );
}
