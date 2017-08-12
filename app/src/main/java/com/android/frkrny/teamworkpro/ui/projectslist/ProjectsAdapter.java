package com.android.frkrny.teamworkpro.ui.projectslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.frkrny.teamworkpro.data.model.Project;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_ID;

/**
 * Created by frankrooney on 12/08/2017.
 * A {@link android.support.v7.widget.RecyclerView} adapter for displaying projects.
 */

class ProjectsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Project> projects;

    ProjectsAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
        setHasStableIds(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProjectView projectView = new ProjectView(context);
        ProjectViewHolder holder = new ProjectViewHolder(projectView.getRootView());
        holder.setProjectView(projectView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Project project = projects.get(position);
        ((ProjectViewHolder)holder).getProjectView().bind(project);
    }

    @Override
    public int getItemCount() {
        return (projects != null ? projects.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        if(projects != null && !projects.isEmpty()) {
            return projects.get(position).getId();
        } else {
            return NO_ID;
        }
    }

    private static class ProjectViewHolder extends RecyclerView.ViewHolder {

        private ProjectView projectView;

        ProjectViewHolder(View view) {
            super(view);
        }

        public ProjectView getProjectView() {
            return projectView;
        }

        void setProjectView(ProjectView projectView) {
            this.projectView = projectView;
        }
    }
}
