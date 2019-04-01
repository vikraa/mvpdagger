package com.lab.mymvp.base.repo;

import android.content.Context;

import com.lab.mymvp.base.dao.CartDao;
import com.lab.mymvp.base.entity.CartItem;

import java.util.List;

public class CartRepo {

    private CartDao mDao;
    private Context mContext;

    public CartRepo(Context context, CartDao dao) {
        this.mDao = dao;
        this.mContext = context;
    }

    public List<CartItem> getAllCartItems() {
        return mDao.findAll();
    }
}
