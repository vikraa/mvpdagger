package com.lab.mymvp.base.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "discounts")
public class Discount {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int mId;

    @ColumnInfo(name = "name")
    String mName;

    @ColumnInfo(name = "discount_value")
    float mDiscountValue;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public float getDiscountValue() {
        return mDiscountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.mDiscountValue = discountValue;
    }
}
