package com.lab.mymvp.base.module;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.lab.mymvp.base.MainContract;
import com.lab.mymvp.base.repo.ItemRepo;
import com.lab.mymvp.base.repo.LibraryRepo;
import com.lab.mymvp.business.AlertFragment;
import com.lab.mymvp.business.MainActivity;
import com.lab.mymvp.business.PresenterImpl;
import com.lab.mymvp.business.left.DiscountListFragment;
import com.lab.mymvp.business.left.ItemListFragment;
import com.lab.mymvp.business.left.LibraryFragment;
import com.lab.mymvp.business.net.RestHelper;
import com.lab.mymvp.business.right.ShoppingCartFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Binds
    public abstract MainContract.View view(MainActivity mainActivity);

    @Binds
    public abstract Fragment fragmentLibrary(LibraryFragment fragment);

    @Binds
    public abstract Fragment fragmentShoppingCart(ShoppingCartFragment fragment);

    @Binds
    public abstract Fragment fragmentItemList(ItemListFragment itemListFragment);

    @Binds
    public abstract Fragment fragmentDiscountList(DiscountListFragment discountListFragment);

    @Binds
    public abstract DialogFragment fragmentDialogCart(AlertFragment alertFragment);

    @Provides
    static RestHelper provideRestHelper() {
        return RestHelper.getInstance();
    }

    @Provides
    static MainContract.Presenter providePresenter(LibraryRepo repo, ItemRepo itemRepo, RestHelper helper, MainContract.View view) {
        return new PresenterImpl(repo, itemRepo, helper, view);
    }
}
