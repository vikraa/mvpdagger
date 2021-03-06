package com.lab.mymvp.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.lab.mymvp.base.dao.CartDao;
import com.lab.mymvp.base.dao.DiscountDao;
import com.lab.mymvp.base.dao.ItemDao;
import com.lab.mymvp.base.dao.LibraryDao;
import com.lab.mymvp.base.entity.CartItem;
import com.lab.mymvp.base.entity.Discount;
import com.lab.mymvp.base.entity.ItemData;
import com.lab.mymvp.base.entity.Library;

@Database(entities = {Library.class, ItemData.class,
                        Discount.class, CartItem.class}, version = RoomDb.VERSION, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    static final int VERSION = 1;

    public abstract LibraryDao getLibraryDao();
    public abstract ItemDao getItemDao();
    public abstract DiscountDao getDiscountDao();
    public abstract CartDao getCartDao();
}
