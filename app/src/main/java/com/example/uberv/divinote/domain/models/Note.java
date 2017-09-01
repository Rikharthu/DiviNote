package com.example.uberv.divinote.domain.models;

import java.util.Date;

public class Note {
    private final long mId;
    private Date mCreatedAt;
    private Date mUpdatedAt;
    private Date mRemindAt;
    private Status mStatus;
    private String mTitle;
    private String mDescription;

    public Note(long id) {
        mId = id;
    }

    public Note(long id, Date createdAt, Date updatedAt, Date remindAt, Status status, String title, String description) {
        mId = id;
        mCreatedAt = createdAt;
        mUpdatedAt = updatedAt;
        mRemindAt = remindAt;
        mStatus = status;
        mTitle = title;
        mDescription = description;
    }

    public long getId() {
        return mId;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Date getRemindAt() {
        return mRemindAt;
    }

    public void setRemindAt(Date remindAt) {
        mRemindAt = remindAt;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public static enum Status {
        COMPLETED, TODO, FINISHED
    }
}
