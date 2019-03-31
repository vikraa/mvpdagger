package com.lab.mymvp.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.lab.mymvp.base.entity.ItemData;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ItemData item);

    @Query("select * from items")
    List<ItemData> getItemDiscount();

    @Query("select * from items")
    List<ItemData> findAll();
}
