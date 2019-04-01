package com.lab.mymvp.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lab.mymvp.base.entity.Discount;

import java.util.List;

@Dao
public interface DiscountDao {

    @Query("select * from discounts")
    List<Discount> findAll();

    @Insert
    long insert(Discount discount);
}
