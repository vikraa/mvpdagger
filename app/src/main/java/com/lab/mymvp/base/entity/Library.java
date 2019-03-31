package com.lab.mymvp.base.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "library")
public class Library {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lib_id")
    private int mId;

    @ColumnInfo(name = "lib_name")
    private String mName;

    @NonNull
    public int getId() {
        return mId;
    }

    public void setId(@NonNull int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
