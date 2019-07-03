package myfirst.agicent.com.myapplication.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;
    static Gson gsonBuilder;
    private static String staging_base_url = "https://www.swapaspot.mobileappdevelopmentservices.com/api/v1/";


    public static Retrofit getClient() /*throws MyIllegalArgumentsException*/ {

        gsonBuilder = new GsonBuilder().serializeNulls().create();
        retrofit = new Retrofit.Builder().
                baseUrl(staging_base_url).
                addConverterFactory(GsonConverterFactory.create())
//                .client(createOkHttpClient())
                .build();

//        }
        return retrofit;
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60 / 2, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(false ? null : null)
                .build();
    }
}
