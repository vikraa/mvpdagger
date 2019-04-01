package com.lab.mymvp.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lab.mymvp.base.entity.CartItem;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    long insert(CartItem cartItem);

    @Query("select * from cart")
    List<CartItem> findAll();

}
