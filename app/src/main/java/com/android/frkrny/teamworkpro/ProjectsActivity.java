package com.android.frkrny.teamworkpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public class ProjectsActivity extends AppCompatActivity {

    private ProgressBar loadingBar;
    private RecyclerView projectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        loadingBar = (ProgressBar) findViewById(R.id.loading_progress);
        projectsList = (RecyclerView) findViewById(R.id.projects_list);
    }
}
