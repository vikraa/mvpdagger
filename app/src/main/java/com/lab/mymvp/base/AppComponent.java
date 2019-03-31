package com.lab.mymvp.base;

import android.app.Application;

import com.lab.mymvp.base.module.ActivityBindingsModule;
import com.lab.mymvp.base.module.AppModule;
import com.lab.mymvp.base.module.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingsModule.class,
        RoomModule.class})

public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
