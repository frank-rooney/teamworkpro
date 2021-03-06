
package com.android.frkrny.teamworkpro.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Project implements Parcelable {

    @SerializedName("company")
    private Company company;
    @SerializedName("starred")
    private boolean starred;
    @SerializedName("name")
    private String name;
    @SerializedName("show-announcement")
    private boolean showAnnouncement;
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
    private boolean notifyEveryone;
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

    public Boolean isStarred() {
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

    public Boolean isShowAnnouncement() {
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

    public boolean isNotifyEveryone() {
        return notifyEveryone;
    }

    public void setNotifyEveryone(boolean notifyEveryone) {
        this.notifyEveryone = notifyEveryone;
    }

    public long getId() {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return -1;
        }
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

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(company, flags);
        dest.writeByte((byte) (starred ? 1 : 0));
        dest.writeString(name);
        dest.writeByte((byte) (showAnnouncement ? 1 : 0));
        dest.writeString(announcement);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(createdOn);
        dest.writeParcelable(category, flags);
        dest.writeString(startPage);
        dest.writeString(startDate);
        dest.writeString(logo);
        dest.writeByte((byte) (notifyEveryone ? 1 : 0));
        dest.writeString(id);
        dest.writeString(lastChangedOn);
        dest.writeString(endDate);
    }

    protected Project(Parcel in) {
        company = in.readParcelable(Company.class.getClassLoader());
        starred = in.readByte() != 0;
        name = in.readString();
        showAnnouncement = in.readByte() != 0;
        announcement = in.readString();
        description = in.readString();
        status = in.readString();
        createdOn = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        startPage = in.readString();
        startDate = in.readString();
        logo = in.readString();
        notifyEveryone = in.readByte() != 0;
        id = in.readString();
        lastChangedOn = in.readString();
        endDate = in.readString();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
