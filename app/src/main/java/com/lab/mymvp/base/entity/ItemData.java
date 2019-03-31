package com.lab.mymvp.base.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "items")
public class ItemData {

    @ColumnInfo(name = "albumId")
    @SerializedName("albumId")
    int mAlbumId;

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    int mId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    String mTitle;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    String mUrl;

    @ColumnInfo(name = "thumbnailUrl")
    @SerializedName("thumbnailUrl")
    String mThumbNailUrl;

    @ColumnInfo(name = "price")
    long mPrice;

    public int getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(int mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getThumbNailUrl() {
        return mThumbNailUrl;
    }

    public void setThumbNailUrl(String mThumbNailUrl) {
        this.mThumbNailUrl = mThumbNailUrl;
    }

    public long getPrice() {
        return mPrice;
    }

    public void setPrice(long mPrice) {
        this.mPrice = mPrice;
    }
}
