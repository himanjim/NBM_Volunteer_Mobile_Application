package com.dot.nbm.doers;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.work.WorkManager;

import com.dot.nbm.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
@Keep
public class RetrofitAPICaller {

    public static void postData(String cnhStatesStr, Context context) {

        // on below line we are creating a retrofit
        // builder and passing our base url
        OkHttpClient.Builder okBuilder = new OkHttpClient().newBuilder();

        okBuilder.hostnameVerifier((hostname, session) -> hostname.equals(context.getString(R.string.retrofit_hostname)));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.retrofit_base_url))
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .client(okBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);


        // calling a method to create a post and passing our modal class.
        if (cnhStatesStr != null && cnhStatesStr.length() > 0) {
            Call<String> call = retrofitAPI.postData(cnhStatesStr);

            // on below line we are executing our method.
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
//                    Log.i("RetrofitAPICaller", response.toString());
                    GsonHandler.emptyCombinedSignalNetworkHardwareStates(context);
                    if (response.body().contains(context.getString(R.string.shutdown))) {
                        WorkManager.getInstance(context).cancelAllWorkByTag(context.getString(R.string.worker_tag));
                        GsonHandler.savePauseShutdownState(context, true);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
//                    Log.e("RetrofitAPICaller", "Error" + t);
                }
            });
        }
    }
}