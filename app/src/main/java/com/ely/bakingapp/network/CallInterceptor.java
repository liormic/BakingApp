package com.ely.bakingapp.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lior on 4/23/2018.
 */

public class CallInterceptor implements Interceptor{


    public CallInterceptor() {
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request sourceRequest = chain.request();
        HttpUrl sourceHttpUrl = sourceRequest.url();
        HttpUrl httpUrl = sourceHttpUrl.newBuilder()
                .build();

        Request request = sourceRequest.newBuilder().url(httpUrl).build();
        return  chain.proceed(request);
    }
}
