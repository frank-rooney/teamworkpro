package com.android.frkrny.teamworkpro.data;

import com.android.frkrny.teamworkpro.data.model.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by frankrooney on 10/08/2017.
 * A class that defines our API endpoints and how to access them etc.
 */

public interface TeamworkApiService {

    String BASE_URL = "http://www.eu.teamwork.com";

    @GET("/projects.json")
    Call<ApiResponse> getActiveProjects();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static TeamworkApiService newTeamworkService() {
            Gson gson = new GsonBuilder().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TeamworkApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(TeamworkApiService.class);
        }
    }
}
