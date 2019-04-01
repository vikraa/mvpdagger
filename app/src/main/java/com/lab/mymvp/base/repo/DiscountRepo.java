package com.lab.mymvp.base.repo;

import android.content.Context;

import com.lab.mymvp.base.dao.DiscountDao;
import com.lab.mymvp.base.entity.Discount;

import java.util.List;

public class DiscountRepo {

    private DiscountDao mDao;
    private Context mContext;

    public DiscountRepo(Context context, DiscountDao dao) {
        this.mContext = context;
        this.mDao = dao;
    }

    public List<Discount> getAllDiscounts() {
        return mDao.findAll();
    }

    public long insert(Discount discount) {
        return mDao.insert(discount);
    }
}
