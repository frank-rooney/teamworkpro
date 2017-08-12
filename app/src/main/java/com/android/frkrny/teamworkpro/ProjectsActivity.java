package com.android.frkrny.teamworkpro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.frkrny.teamworkpro.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsActivity extends AppCompatActivity implements Callback<ApiResponse> {

    private ProgressBar loadingBar;
    private RecyclerView projectsList;
    private Call<ApiResponse> apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        getUIReferences();
        makeGetProjectsApiCall();
    }

    private void getUIReferences() {
        loadingBar = (ProgressBar) findViewById(R.id.loading_progress);
        projectsList = (RecyclerView) findViewById(R.id.projects_list);
    }

    private void makeGetProjectsApiCall() {
        loadingBar.setVisibility(View.VISIBLE);
        apiCall = ((ProjectApplication) getApplication()).getTeamworkApiService().getActiveProjects();
        if (!apiCall.isExecuted()) {
            apiCall.enqueue(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelApiRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelApiRequest();
    }

    private void cancelApiRequest() {
        if (apiCall != null && !apiCall.isExecuted()) {
            apiCall.cancel();
        }
    }


    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call     The call that was made
     * @param response The response from the call
     */
    @Override
    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
        if (response.isSuccessful()) {
            loadingBar.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call The call that was attempted
     * @param t    The error that stopped the call from working.
     */
    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        Toast.makeText(ProjectsActivity.this, R.string.error_message, Toast.LENGTH_LONG).show();
    }
}
