package com.lab.mymvp.base.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "cart")
public class CartItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item_name")
    String mItemName;

    @ColumnInfo(name = "discount_value")
    float mDiscountValue;

    @ColumnInfo(name = "quantity")
    int mQuantity;

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public float getDiscountValue() {
        return mDiscountValue;
    }

    public void setDiscountValue(float mDiscountValue) {
        this.mDiscountValue = mDiscountValue;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}
