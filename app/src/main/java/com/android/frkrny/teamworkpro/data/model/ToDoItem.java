package com.android.frkrny.teamworkpro.data.model;

/**
 * Created by frankrooney on 13/08/2017.
 * Model for adding tasks. This json will be posted.
 */

public class ToDoItem {

    private String content;

    public ToDoItem(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
