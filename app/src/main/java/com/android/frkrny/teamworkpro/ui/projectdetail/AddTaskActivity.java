package com.android.frkrny.teamworkpro.ui.projectdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.frkrny.teamworkpro.ProjectApplication;
import com.android.frkrny.teamworkpro.R;
import com.android.frkrny.teamworkpro.data.model.ApiResponse;
import com.android.frkrny.teamworkpro.data.model.Project;
import com.android.frkrny.teamworkpro.data.model.TaskList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.frkrny.teamworkpro.R.id.fab;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener, Callback<ApiResponse> {

    public static final String KEY_PROJECT = "key_project";
    private ViewGroup root;
    private TextInputLayout inputLayout;
    private TextInputEditText newTaskName;
    private Spinner taskLists;
    private FloatingActionButton addTask;
    private Call<ApiResponse> apiCallTaskLists, apiCallAddTask;
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        retrieveProjectFromIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getUIReferences();
        setSupportActionBar(toolbar);
        addTask.setOnClickListener(this);
        makeGetTaskListsApiCall();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelApiRequests();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelApiRequests();
    }

    private void retrieveProjectFromIntent() {
        if(!getIntent().getExtras().isEmpty()) {
            project = getIntent().getParcelableExtra(KEY_PROJECT);
        } else {
            finish();
        }
    }

    private void cancelApiRequests() {
        if (apiCallTaskLists != null) {
            apiCallTaskLists.cancel();
        } if(apiCallAddTask != null) {
            apiCallAddTask.cancel();
        }
    }

    private void makeGetTaskListsApiCall() {
        apiCallTaskLists = ((ProjectApplication) getApplication()).getTeamworkApiService().getTaskListsForProject(String.valueOf(project.getId()));
        if (!apiCallTaskLists.isExecuted()) {
            apiCallTaskLists.enqueue(this);
        }
    }

    private void getUIReferences() {
        root = (ViewGroup) findViewById(R.id.root);
        addTask = (FloatingActionButton) findViewById(fab);
        inputLayout = (TextInputLayout) findViewById(R.id.new_task_name_holder);
        newTaskName = (TextInputEditText) findViewById(R.id.new_task_name);
        taskLists = (Spinner) findViewById(R.id.task_lists_spinner);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(validateFieldsNotEmpty()) {
            TaskList selected = getSelectedTaskList();
            apiCallAddTask = ((ProjectApplication) getApplication()).getTeamworkApiService().addTaskToTaskList(selected.getId(), newTaskName.getText().toString());
            apiCallAddTask.enqueue(this);
        }
    }

    private boolean validateFieldsNotEmpty() {
        if(newTaskName.getText().length() == 0) {
            inputLayout.setError("Task name cannot be empty.");
            return false;
        } if(getSelectedTaskList() == null) {
            Snackbar.make(root, R.string.error_no_task_list_selected, Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private TaskList getSelectedTaskList() {
        TaskList selectedTaskList = (TaskList) taskLists.getSelectedItem();
        if(!selectedTaskList.getId().equals("-1")) {
            return selectedTaskList;
        }
        return null;
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
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
        if (response.isSuccessful()) {
            if(response.body().getTaskLists() != null) {
                setupTaskListsSpinner(response);
            } if (response.code() == 201) {
                Snackbar.make(root, R.string.task_added_ok, Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(root, response.message(), Snackbar.LENGTH_LONG).show();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTaskListsSpinner(@NonNull Response<ApiResponse> response) {
        ArrayAdapter<TaskList> taskListsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, response.body().getTaskLists());
        taskLists.setAdapter(taskListsAdapter);
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call The call that was attempted
     * @param t    The error that stopped the call from working.
     */
    @Override
    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
        Snackbar.make(root, R.string.error_message, Snackbar.LENGTH_LONG).show();
    }
}
