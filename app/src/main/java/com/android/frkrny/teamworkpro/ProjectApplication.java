package com.android.frkrny.teamworkpro;

import android.app.Application;

import com.android.frkrny.teamworkpro.data.TeamworkApiService;

/**
 * Created by frankrooney on 12/08/2017.
 * An {@link Application} subclass that provides access to our Retrofit Service for making
 * web service calls and other useful things.
 */

public class ProjectApplication extends Application {

    private TeamworkApiService teamworkApiService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public TeamworkApiService getTeamworkApiService() {
        if(teamworkApiService == null) {
            teamworkApiService = TeamworkApiService.Creator.newTeamworkService();
        }
        return teamworkApiService;
    }
}
