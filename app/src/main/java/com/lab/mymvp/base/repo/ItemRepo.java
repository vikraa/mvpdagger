package com.lab.mymvp.base.repo;

import android.content.Context;

import com.lab.mymvp.base.dao.ItemDao;
import com.lab.mymvp.base.entity.ItemData;

import java.util.List;

public class ItemRepo {

    private ItemDao mDao;
    private Context mContext;

    public ItemRepo(Context context, ItemDao dao) {
        this.mDao = dao;
        this.mContext = context;
    }

    public List<ItemData> getAllItems() {
        return mDao.findAll();
    }

    public List<ItemData> getAllDiscount() {
        return mDao.getItemDiscount();
    }

    public long insert(ItemData item) {
        return mDao.insert(item);
    }

}

