package com.example.robertoferro.diary.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "diary_entry_table")
public class DiaryEntries implements Serializable {

    public static final String INTENT_IDENTIFIER_DAIRY_ENTRY_OBJECT = "DairyEntryObject";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "identifier")
    private int mIdentifier;

    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "body")
    private String mBody;

    @NonNull
    @ColumnInfo(name = "date")
    private Date mDate;

    public DiaryEntries(@NonNull String mId, @NonNull String mTitle, @NonNull String mBody, @NonNull Date mDate) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mBody = mBody;
        this.mDate = mDate;
    }

    @NonNull
    public int getIdentifier() {return mIdentifier; }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getBody() {
        return mBody;
    }

    @NonNull
    public Date getDate() {
        return mDate;
    }

    public void setIdentifier(@NonNull int mIdentifier) {
        this.mIdentifier = mIdentifier;
    }
}
