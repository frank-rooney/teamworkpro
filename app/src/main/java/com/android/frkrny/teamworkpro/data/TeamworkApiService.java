package com.android.frkrny.teamworkpro.data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by frankrooney on 10/08/2017.
 * A class that defines our API endpoints and how to access them etc.
 */

public interface TeamworkApiService {

    String API_END_POINT = "";
    @GET("")
    Call<List<Project>> getProjects();
}
