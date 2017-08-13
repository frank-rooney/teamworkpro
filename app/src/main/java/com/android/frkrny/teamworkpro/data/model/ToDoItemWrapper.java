package com.android.frkrny.teamworkpro.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frankrooney on 13/08/2017.
 * Model for adding tasks. This json will be posted.
 */

public class ToDoItemWrapper {

    @SerializedName("content")
    private String content;
    @SerializedName("tasklistId")
    private String taskListId;
    @SerializedName("creator-id")
    private String creatorId;
    @SerializedName("notify")
    private boolean notify;
    @SerializedName("private")
    private boolean pPrivate;
    @SerializedName("todo-item")
    private ToDoItem toDoItem;

    public ToDoItemWrapper(String content, String taskListId) {
        this.content = content;
        this.taskListId = taskListId;
        this.toDoItem = new ToDoItem();
    }

    public String getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(String taskListId) {
        this.taskListId = taskListId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public boolean ispPrivate() {
        return pPrivate;
    }

    public void setpPrivate(boolean pPrivate) {
        this.pPrivate = pPrivate;
    }

    public ToDoItem getToDoItem() {
        return toDoItem;
    }

    public void setToDoItem(ToDoItem toDoItem) {
        this.toDoItem = toDoItem;
    }
}
