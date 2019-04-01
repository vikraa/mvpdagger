package com.lab.mymvp.base.repo;

import android.content.Context;

import com.lab.mymvp.R;
import com.lab.mymvp.base.dao.DiscountDao;
import com.lab.mymvp.base.entity.Discount;

import java.util.List;

public class DiscountRepo {

    private DiscountDao mDao;
    private Context mContext;

    public DiscountRepo(Context context, DiscountDao dao) {
        this.mContext = context;
        this.mDao = dao;
        init();
    }

    private void init() {
        List<Discount> discounts = mDao.findAll();
        if (discounts == null || discounts.isEmpty()) {
            String[] names = mContext.getResources().getStringArray(R.array.discount_name);
            String[] values = mContext.getResources().getStringArray(R.array.discount_values);
            for (int i = 0; i < names.length; i++) {
                Discount discount = new Discount();
                discount.setName(names[i]);
                discount.setDiscountValue(Float.valueOf(values[i]));
                mDao.insert(discount);
            }
        }
    }

    public List<Discount> getAllDiscounts() {
        return mDao.findAll();
    }

    public long insert(Discount discount) {
        return mDao.insert(discount);
    }
}
