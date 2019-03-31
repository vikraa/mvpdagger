package com.lab.mymvp.business.net;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestHelper {

    private static final long CONNECTION_TIMEOUT = 30000; //milliseconds
    private static final long CONNECTION_READ_TIMEOUT = 30000; //milliseconds
    private static final String END_POINT = "https://jsonplaceholder.typicode.com";
    private static RestHelper sRestHelper;

    public static RestHelper getInstance() {
        if(sRestHelper == null) {
            sRestHelper = new RestHelper();
        }
        return sRestHelper;
    }

    private RestHelper() {

    }

    public Rest buildRestService() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(getOkClient())
                .setEndpoint(END_POINT)
                .setLogLevel(RestAdapter.LogLevel.BASIC);
        return builder.build().create(Rest.class);

    }

    private OkClient getOkClient() {
        OkHttpClient okHttpClient = new OkHttpClient() {
            @Override
            public Call newCall(Request request) {
                Request.Builder builder = request.newBuilder();
                builder.tag(new Object());
                return super.newCall(builder.build());
            }
        };
        okHttpClient.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(CONNECTION_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        return new OkClient(okHttpClient);
    }
}
