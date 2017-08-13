package com.android.frkrny.teamworkpro.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frankrooney on 13/08/2017.
 * Model for adding tasks. This json will be posted.
 */

public class ToDoItem {

    @SerializedName("responsible-party-id")
    private String responsiblePartyId;
    @SerializedName("start-date")
    private String startDate;
    @SerializedName("due-date")
    private String dueDate;

    public ToDoItem() {
        this.responsiblePartyId = "0";
        this.startDate = "";
        this.dueDate = "";
    }

    public String getResponsiblePartyId() {
        return responsiblePartyId;
    }

    public void setResponsiblePartyId(String responsiblePartyId) {
        this.responsiblePartyId = responsiblePartyId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
