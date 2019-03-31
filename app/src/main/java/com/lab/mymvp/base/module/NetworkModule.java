package com.lab.mymvp.base.module;

import com.lab.mymvp.business.net.RestHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    RestHelper provideRestHelper() {
        return RestHelper.getInstance();
    }
}
