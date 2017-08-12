
package com.android.frkrny.teamworkpro.data.model;

import com.google.gson.annotations.SerializedName;

public class Project {

    @SerializedName("company")
    private Company company;
    @SerializedName("starred")
    private Boolean starred;
    @SerializedName("name")
    private String name;
    @SerializedName("show-announcement")
    private Boolean showAnnouncement;
    @SerializedName("announcement")
    private String announcement;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private String status;
    @SerializedName("created-on")
    private String createdOn;
    @SerializedName("category")
    private Category category;
    @SerializedName("start-page")
    private String startPage;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("logo")
    private String logo;
    @SerializedName("notifyeveryone")
    private Boolean notifyEveryone;
    @SerializedName("id")
    private String id;
    @SerializedName("last-changed-on")
    private String lastChangedOn;
    @SerializedName("endDate")
    private String endDate;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShowAnnouncement() {
        return showAnnouncement;
    }

    public void setShowAnnouncement(Boolean showAnnouncement) {
        this.showAnnouncement = showAnnouncement;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getNotifyEveryone() {
        return notifyEveryone;
    }

    public void setNotifyEveryone(Boolean notifyeveryone) {
        this.notifyEveryone = notifyeveryone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastChangedOn() {
        return lastChangedOn;
    }

    public void setLastChangedOn(String lastChangedOn) {
        this.lastChangedOn = lastChangedOn;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
