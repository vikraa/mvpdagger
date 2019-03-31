package com.lab.mymvp.business;

import android.os.Bundle;

import com.lab.mymvp.R;
import com.lab.mymvp.base.MainContract;
import com.lab.mymvp.base.entity.Library;
import com.lab.mymvp.business.left.ItemListFragment;
import com.lab.mymvp.business.left.LibraryFragment;
import com.lab.mymvp.business.right.ShoppingCartFragment;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View<Library> {

    @Inject
    LibraryFragment mLibraryFragment;

    @Inject
    ShoppingCartFragment mShoppingCartFragment;

    @Inject
    ItemListFragment mItemListFragment;

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

    @Override
    public void showData(List<Library> list) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //mPresenter.initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mPresenter.unsubscribe();
    }
}
