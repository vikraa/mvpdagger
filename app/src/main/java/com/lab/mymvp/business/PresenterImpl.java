package com.lab.mymvp.business;

import android.util.Log;

import com.lab.mymvp.base.MainContract;
import com.lab.mymvp.base.entity.ItemData;
import com.lab.mymvp.base.repo.ItemRepo;
import com.lab.mymvp.base.repo.LibraryRepo;
import com.lab.mymvp.business.net.RestHelper;

import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PresenterImpl implements MainContract.Presenter {

    private MainContract.View mView;
    private RestHelper mRestHelper;
    private LibraryRepo mLibraryRepo;
    private ItemRepo mItemRepo;

    public PresenterImpl(LibraryRepo repo, ItemRepo itemRepo, RestHelper restHelper, MainContract.View view) {
        this.mView = view;
        this.mRestHelper = restHelper;
        this.mLibraryRepo = repo;
        this.mItemRepo = itemRepo;
    }

    @Override
    public void initData() {
        mRestHelper.buildRestService().allItems(new Callback<List<ItemData>>() {
            @Override
            public void success(List<ItemData> items, Response response) {
                Log.d("retrofit success", "count  = " + items.size());
                for (ItemData it : items) {
                    int rand = new Random().nextInt((99 - 10) + 1) + 10;
                    it.setPrice(it.getId()*rand);
                    mItemRepo.insert(it);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit failed", error.getCause().getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        //mView.showData("unsubscribed");
    }
}
