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

    @Query("delete from cart")
    void delete();

    @Query("select item_id, item_name, discount_value, sum(item_prices) as item_prices, sum(quantity) as quantity " +
            "from cart group by item_name, discount_value")
    List<CartItem> findAll();

}
