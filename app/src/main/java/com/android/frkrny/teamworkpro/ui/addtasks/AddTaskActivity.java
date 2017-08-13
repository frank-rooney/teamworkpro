package com.android.frkrny.teamworkpro.ui.addtasks;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.android.frkrny.teamworkpro.ProjectApplication;
import com.android.frkrny.teamworkpro.R;
import com.android.frkrny.teamworkpro.custom.Constants;
import com.android.frkrny.teamworkpro.data.model.ApiResponse;
import com.android.frkrny.teamworkpro.data.model.Project;
import com.android.frkrny.teamworkpro.data.model.TaskList;
import com.android.frkrny.teamworkpro.data.model.ToDoItemWrapper;

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
    private Toolbar toolbar;
    private ProgressBar loading;
    private NestedScrollView parent;
    private FloatingActionButton addTask;
    private ImageButton showInfo;
    private Call<ApiResponse> apiCallTaskLists, apiCallAddTask;
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        retrieveProjectFromIntent();
        getUIReferences();
        setSupportActionBar(toolbar);
        displayProjectDetails();
        setClickListeners();
        makeGetTaskListsApiCall();
    }

    private void setClickListeners() {
        addTask.setOnClickListener(this);
        showInfo.setOnClickListener(this);
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

    @SuppressWarnings("ConstantConditions")
    private void displayProjectDetails() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(project.getName() + getString(R.string.title_add_tasks));
    }

    /**
     * Uses a circular reveal animation to show the edit text and spinner
     * when the task lists have loaded. Defaults to fading in the views pre lollipop.
     */
    private void animateReveal() {
        loading.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            parent.postDelayed(new Runnable() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    // get the center for the clipping circle
                    int cx = parent.getWidth() / 2;
                    int cy = parent.getHeight() / 2;
                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);
                    // create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(parent, cx, cy, 0, finalRadius);
                    // make the view visible and start the animation
                    parent.setVisibility(View.VISIBLE);
                    anim.start();
                }
            }, Constants.MEDIUM_ANIM);
        } else {
            fadeInView(parent);
        }
    }

    /**
     * Starts an animation to fade in a view.
     *
     * @param view The View to fade in
     */
    private void fadeInView(final View view) {
        if (view != null && view.getVisibility() == View.INVISIBLE ||
                view != null && view.getVisibility() == View.GONE) {
            view.setAlpha(Constants.TRANSPARENT);
            view.setVisibility(View.VISIBLE);
            view.animate().alpha(Constants.OPAQUE).setDuration(Constants.MEDIUM_ANIM).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.VISIBLE);
                }
            }).start();
        }
    }

    private void retrieveProjectFromIntent() {
        if (!getIntent().getExtras().isEmpty() && getIntent().hasExtra(KEY_PROJECT)) {
            project = getIntent().getParcelableExtra(KEY_PROJECT);
        } else {
            finish();
        }
    }

    private void cancelApiRequests() {
        if (apiCallTaskLists != null) {
            apiCallTaskLists.cancel();
        }
        if (apiCallAddTask != null) {
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        root = (ViewGroup) findViewById(R.id.root);
        addTask = (FloatingActionButton) findViewById(fab);
        inputLayout = (TextInputLayout) findViewById(R.id.new_task_name_holder);
        newTaskName = (TextInputEditText) findViewById(R.id.new_task_name);
        taskLists = (Spinner) findViewById(R.id.task_lists_spinner);
        loading = (ProgressBar) findViewById(R.id.loading_progress);
        parent = (NestedScrollView) findViewById(R.id.nested_scrollview_parent);
        showInfo = (ImageButton) findViewById(R.id.show_info);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (validateFieldsNotEmpty()) {
                    TaskList selected = getSelectedTaskList();
                    ToDoItemWrapper toDoItemWrapper = new ToDoItemWrapper(newTaskName.getText().toString(), selected.getId());
                    apiCallAddTask = ((ProjectApplication) getApplication()).getTeamworkApiService().addTasksToTaskList(selected.getId(), toDoItemWrapper);
                    apiCallAddTask.enqueue(this);
                }
                break;
            case R.id.show_info:
                Snackbar.make(root, R.string.multiple_tasks_tip, Snackbar.LENGTH_INDEFINITE).show();
                break;
        }
    }

    /**
     * Performs some validation to prevent invalid api calls.
     * @return true if we can make an api call, false otherwise.
     */
    private boolean validateFieldsNotEmpty() {
        if (newTaskName.getText().length() == 0) {
            inputLayout.setError(getString(R.string.error_task_name_cannot_be_empty));
            return false;
        }
        if (getSelectedTaskList() == null) {
            Snackbar.make(root, R.string.error_no_task_list_selected, Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private TaskList getSelectedTaskList() {
        TaskList selectedTaskList = (TaskList) taskLists.getSelectedItem();
        if (selectedTaskList != null && !selectedTaskList.getId().equals("-1")) {
            return selectedTaskList;
        }
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTaskListsSpinner(@NonNull Response<ApiResponse> response) {
        ArrayAdapter<TaskList> taskListsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, response.body().getTaskLists());
        taskLists.setAdapter(taskListsAdapter);
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
            if (response.body().getTaskLists() != null) {
                setupTaskListsSpinner(response);
                animateReveal();
                return;
            }
            if (response.code() == 200) {
                newTaskName.setText("");
                Snackbar.make(root, R.string.task_added_ok, Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(root, response.message(), Snackbar.LENGTH_INDEFINITE).show();
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
    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
        Snackbar.make(root, R.string.error_message, Snackbar.LENGTH_INDEFINITE).show();
    }
}
