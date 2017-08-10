package com.android.frkrny.teamworkpro.data;

import com.android.frkrny.teamworkpro.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by frankrooney on 10/08/2017.
 * A class that defines our API endpoints and how to access them etc.
 */

public interface TeamworkApiService {

    String BASE_URL = "http://www.eu.teamwork.com";

    @GET("/projects.json")
    Call<ApiResponse> getProjects();


}
