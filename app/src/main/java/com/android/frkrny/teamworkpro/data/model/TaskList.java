package com.android.frkrny.teamworkpro.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frankrooney on 13/08/2017.
 * A class that models a task list for a project.
 */
public class TaskList {

    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("complete")
    public boolean complete;
    @SerializedName("id")
    public String id;

    public TaskList() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
