package com.lab.mymvp.business;

import android.os.Bundle;

import com.lab.mymvp.R;
import com.lab.mymvp.base.MainContract;
import com.lab.mymvp.base.entity.ItemData;
import com.lab.mymvp.base.entity.Library;
import com.lab.mymvp.base.repo.CartRepo;
import com.lab.mymvp.base.repo.ItemRepo;
import com.lab.mymvp.business.left.DiscountListFragment;
import com.lab.mymvp.business.left.ItemListFragment;
import com.lab.mymvp.business.left.LibraryFragment;
import com.lab.mymvp.business.right.ShoppingCartFragment;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View<ItemData> {

    @Inject
    LibraryFragment mLibraryFragment;

    @Inject
    ShoppingCartFragment mShoppingCartFragment;

    @Inject
    ItemListFragment mItemListFragment;

    @Inject
    DiscountListFragment mDiscountListFragment;

    @Inject
    ItemRepo mItemRepo;

    @Inject
    CartRepo mCartRepo;

    @Inject
    MainContract.Presenter mPresenter;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    private void initFragments() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.left_fragment,mLibraryFragment)
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.right_fragment,mShoppingCartFragment)
                .commit();
    }

    public void showFragmentAllItems() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.left_fragment, mItemListFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showFragmentDiscounts() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.left_fragment, mDiscountListFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showData(List<ItemData> list) {
        for (ItemData it : list) {
            int rand = new Random().nextInt((99 - 10) + 1) + 10;
            it.setPrice(it.getId()*rand);
            mItemRepo.insert(it);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.initData();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
