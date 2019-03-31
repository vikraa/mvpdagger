package com.lab.mymvp.base.module;

import com.lab.mymvp.base.PerActivity;
import com.lab.mymvp.business.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ActivityBindingsModule {

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();

}
