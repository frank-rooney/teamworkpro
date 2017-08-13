package com.android.frkrny.teamworkpro.ui.projectslist;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.frkrny.teamworkpro.R;
import com.android.frkrny.teamworkpro.custom.RoundedTransformBuilder;
import com.android.frkrny.teamworkpro.data.model.Project;
import com.android.frkrny.teamworkpro.ui.projectdetail.AddTaskActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by frankrooney on 12/08/2017.
 * A {@link CardView} subclass that inflates a custom layout and handles binding data for a project.
 */

public class ProjectView extends CardView {

    private static final String PROJECT_DATE_FORMAT = "yyyyMMdd";
    private TextView projectName, companyName, description, startDate, endDate, categoryLabel;
    private ImageView projectLogo, staredIcon, notifyAllIcon;
    private Transformation transformation;
    private Project project;

    public ProjectView(Context context) {
        super(context);
        setupCardViewProperties();
        setupClickListener();
        inflateLayoutAndSetLayoutParams(context);
        getUIReferences();
        setupRoundedLogoTransformation();
    }

    private void setupClickListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTaskIntent = new Intent(getContext(), AddTaskActivity.class);
                addTaskIntent.putExtra(AddTaskActivity.KEY_PROJECT, project);
                getContext().startActivity(addTaskIntent);
            }
        });
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), R.animator.card_lift));
        }
        setClickable(true);
        setUseCompatPadding(true);
        setRadius(5f);
        setElevation(2f);
    }

    private void getUIReferences() {
        projectName = (TextView) findViewById(R.id.project_name);
        companyName = (TextView) findViewById(R.id.project_company);
        description = (TextView) findViewById(R.id.project_description);
        startDate = (TextView) findViewById(R.id.project_start_date);
        endDate = (TextView) findViewById(R.id.project_end_date);
        categoryLabel = (TextView) findViewById(R.id.project_category);
        projectLogo = (ImageView) findViewById(R.id.project_logo);
        staredIcon = (ImageView) findViewById(R.id.project_starred);
        notifyAllIcon = (ImageView) findViewById(R.id.project_notify_all);
    }

    public void bind(Project project) {
        this.project = project;
        displayTitle();
        displayCompany();
        displayDescription();
        displayLogo();
        displayStatusIcons();
        displayStartDate();
        displayDueDate();
        displayCategory();
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

    private void displayStatusIcons() {
        staredIcon.setVisibility((project.isStarred() ? View.VISIBLE : View.INVISIBLE));
        notifyAllIcon.setVisibility((project.isNotifyEveryone() ? View.VISIBLE : View.INVISIBLE));
    }

    /**
     * 20170810 format of String
     */
    private void displayStartDate() {
        if(!TextUtils.isEmpty(project.getStartDate())) {
            SimpleDateFormat receivedFormat = new SimpleDateFormat(PROJECT_DATE_FORMAT, Locale.UK);
            try {
                Date projectStartDate = receivedFormat.parse(project.getStartDate());
                startDate.setText(DateUtils.getRelativeTimeSpanString(getContext(), projectStartDate.getTime()));
                applyTintToLeftDrawable(startDate, R.color.start_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            startDate.setVisibility(View.GONE);
        }
    }

    /**
     * 20170810 format of String
     */
    private void displayDueDate() {
        if(!TextUtils.isEmpty(project.getEndDate())) {
            SimpleDateFormat receivedFormat = new SimpleDateFormat(PROJECT_DATE_FORMAT, Locale.UK);
            try {
                Date projectEndDate = receivedFormat.parse(project.getEndDate());
                endDate.setText(DateUtils.formatDateTime(getContext(), projectEndDate.getTime(), 0));
                applyTintToLeftDrawable(endDate, R.color.due_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            endDate.setVisibility(View.GONE);
        }
    }

    /**
     * Applies a tint to the drawable left icon of the passed TextView.
     */
    private void applyTintToLeftDrawable(TextView textView, @ColorRes int color) {
        Drawable icon = textView.getCompoundDrawables()[0];
        Drawable wrapped = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(wrapped.mutate(), ContextCompat.getColor(getContext(), color));
        textView.setCompoundDrawables(wrapped, null, null, null);
    }

    private void displayCategory() {
        if(!TextUtils.isEmpty(project.getCategory().getName())) {
            categoryLabel.setText(project.getCategory().getName());
        } else {
            categoryLabel.setVisibility(View.GONE);
        }
    }
}
