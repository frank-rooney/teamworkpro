package com.android.frkrny.teamworkpro.ui.projectslist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.frkrny.teamworkpro.R;
import com.android.frkrny.teamworkpro.custom.RoundedTransformBuilder;
import com.android.frkrny.teamworkpro.data.model.Project;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by frankrooney on 12/08/2017.
 * A {@link CardView} subclass that inflates a custom layout and handles binding data for a project.
 */

public class ProjectView extends CardView {

    private ImageView projectLogo;
    private TextView projectName, companyName, description;
    private Transformation transformation;
    private Project project;

    public ProjectView(Context context) {
        super(context);
        setupCardViewProperties();
        inflateLayoutAndSetLayoutParams(context);
        getUIReferences();
        setupRoundedLogoTransformation();
    }

    private void setupRoundedLogoTransformation() {
        transformation = new RoundedTransformBuilder()
                .cornerRadiusDp(48f)
                .build();
    }

    private void inflateLayoutAndSetLayoutParams(Context context) {
        LayoutInflater.from(context).inflate(R.layout.project_row, this, true);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    private void setupCardViewProperties() {
        setUseCompatPadding(true);
        setRadius(5f);
        setElevation(2f);
    }

    private void getUIReferences() {
        projectName = (TextView) findViewById(R.id.project_name);
        companyName = (TextView) findViewById(R.id.project_company);
        description = (TextView) findViewById(R.id.project_description);
        projectLogo = (ImageView) findViewById(R.id.project_logo);
    }

    public void bind(Project project) {
        this.project = project;
        displayTitle();
        displayCompany();
        displayDescription();
        displayLogo();
    }

    private void displayTitle() {
        projectName.setText(project.getName());
    }

    private void displayCompany() {
        companyName.setText(project.getCompany().getName());
    }

    private void displayDescription() {
        description.setText(project.getDescription());
    }

    private void displayLogo() {
        if(!TextUtils.isEmpty(project.getLogo())) {
            Picasso.with(getContext()).load(project.getLogo()).fit().transform(transformation).into(projectLogo);
        } else {
            projectLogo.setImageResource(R.drawable.ic_logo_placeholder);
        }
    }
}
