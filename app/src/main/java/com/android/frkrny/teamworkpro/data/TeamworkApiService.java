package com.android.frkrny.teamworkpro.data;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.android.frkrny.teamworkpro.BuildConfig;
import com.android.frkrny.teamworkpro.data.model.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by frankrooney on 10/08/2017.
 * A class that defines our API endpoints and how to access them etc.
 */

public interface TeamworkApiService {

    String BASE_URL = "https://yat.teamwork.com";

    @GET("/projects.json")
    Call<ApiResponse> getActiveProjects();

    @GET("/projects/{project_id}/tasklists.json")
    Call<ApiResponse> getTaskListsForProject(@Path("project_id") String projectId);

    @POST("/tasklists/{id}/tasks.json")
    Call<ApiResponse> addTaskToTaskList(@Path("id") String taskListId, @Field("content") String content);


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static TeamworkApiService newTeamworkService() {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(new AuthenticationInterceptor())
                    .addInterceptor(interceptor);

            Gson gson = new GsonBuilder().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(TeamworkApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(TeamworkApiService.class);
        }
    }

    /**
     * Interceptor to ensure all our Api calls have the basic auth token included as a header field.
     */
    class AuthenticationInterceptor implements Interceptor {

        private String authToken;

        AuthenticationInterceptor() {
            String t = BuildConfig.API_TOKEN;
            String plainToken = String.format(Locale.UK, "%s:%s", BuildConfig.API_TOKEN, "123abc");
            String base64Token;
            try {
                base64Token = Base64.encodeToString(plainToken.getBytes("utf-8"), Base64.NO_WRAP);
                this.authToken = String.format(Locale.UK, "Basic %s", base64Token);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();

            Request.Builder builder = original.newBuilder()
                    .header("Authorization", authToken);

            Request request = builder.build();
            return chain.proceed(request);
        }
    }
}
