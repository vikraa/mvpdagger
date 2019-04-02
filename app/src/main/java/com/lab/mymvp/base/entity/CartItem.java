package com.lab.mymvp.base.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "cart")
public class CartItem implements Parcelable {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "item_id" )
    int mId;

    @ColumnInfo(name = "item_name")
    String mItemName;

    @ColumnInfo(name = "discount_value")
    float mDiscountValue;

    @ColumnInfo(name = "quantity")
    int mQuantity;

    @ColumnInfo(name = "item_prices")
    float mPrice;

    public CartItem() {
    }

    public CartItem(Parcel in) {
        this.mItemName = in.readString();
        this.mDiscountValue = in.readFloat();
        this.mQuantity = in.readInt();
        this.mPrice = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mItemName);
        dest.writeFloat(mDiscountValue);
        dest.writeInt(mQuantity);
        dest.writeFloat(mPrice);
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel source) {
            return new CartItem(source);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    @NonNull
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        this.mItemName = itemName;
    }

    public float getDiscountValue() {
        return mDiscountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.mDiscountValue = discountValue;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        this.mPrice = price;
    }
}
