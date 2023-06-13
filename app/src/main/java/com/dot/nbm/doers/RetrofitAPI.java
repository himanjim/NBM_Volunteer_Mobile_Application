package com.dot.nbm.doers;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("cnhStates")
    //on below line we are creating a method to post our data.
    Call<String> postData(@Body Map<String, Object> combinedSignalNetworkHardwareState);
}