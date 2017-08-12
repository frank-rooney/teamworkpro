package com.android.frkrny.teamworkpro.ui.projectslist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.frkrny.teamworkpro.R;
import com.android.frkrny.teamworkpro.data.model.Project;
import com.squareup.picasso.Picasso;

/**
 * Created by frankrooney on 12/08/2017.
 * A {@link CardView} subclass that inflates a custom layout and handles binding data for a project.
 */

public class ProjectView extends CardView {

    private ImageView projectLogo;
    private TextView projectName;
    private Project project;

    public ProjectView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.project_row, this, true);
        projectName = (TextView) findViewById(R.id.project_name);
        projectLogo = (ImageView) findViewById(R.id.project_logo);
    }

    public void bind(Project project) {
        this.project = project;
        displayTitle();
        displayLogo();
    }

    private void displayTitle() {
        projectName.setText(project.getName());
    }

    private void displayLogo() {
        if(!TextUtils.isEmpty(project.getLogo())) {
            Picasso.with(getContext()).load(project.getLogo()).fit().into(projectLogo);
        }
    }
}
