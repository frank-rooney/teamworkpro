package com.android.frkrny.teamworkpro.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frankrooney on 13/08/2017.
 * Model for adding tasks. This json will be posted.
 */

public class ToDoItemWrapper {

    @SerializedName("todo-item")
    private ToDoItem toDoItem;

    public ToDoItemWrapper(String content) {
        this.toDoItem = new ToDoItem(content);
    }
}
