package com.example.uberv.divinote.storage.models;


/**
 * Model use by database (storage layer)
 */
// TODO add  more info
public class NoteEntity {
    private long mId;
    private long mCreatedAt;
    private long mUpdatedAt;
    private long mRemindAt;
    private String mStatus;
    private String mTitle;
    private String mDescription;

    public NoteEntity() {
    }

    public NoteEntity(long id, long createdAt, long updatedAt, long remindAt, String status, String title, String description) {
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

    public void setId(long id) {
        mId = id;
    }

    public long getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(long createdAt) {
        mCreatedAt = createdAt;
    }

    public long getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public long getRemindAt() {
        return mRemindAt;
    }

    public void setRemindAt(long remindAt) {
        mRemindAt = remindAt;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
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
}
