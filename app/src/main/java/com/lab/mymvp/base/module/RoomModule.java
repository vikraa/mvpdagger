package com.lab.mymvp.base.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.lab.mymvp.base.RoomDb;
import com.lab.mymvp.base.dao.DiscountDao;
import com.lab.mymvp.base.dao.ItemDao;
import com.lab.mymvp.base.dao.LibraryDao;
import com.lab.mymvp.base.repo.DiscountRepo;
import com.lab.mymvp.base.repo.ItemRepo;
import com.lab.mymvp.base.repo.LibraryRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Singleton
    @Provides
    RoomDb provideRoomDb(Context context) {
        return Room.databaseBuilder(context,
                RoomDb.class, "mvpdagger-db")
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    LibraryDao provideLibraryDao(RoomDb db) {
        return db.getLibraryDao();
    }

    @Singleton
    @Provides
    LibraryRepo provideLibraryRepository(Context context, LibraryDao dao) {
        return new LibraryRepo(context, dao);
    }

    @Singleton
    @Provides
    ItemDao provideItemDao(RoomDb db) {
        return db.getItemDao();
    }

    @Singleton
    @Provides
    ItemRepo provideItemRepository(Context context, ItemDao dao) {
        return new ItemRepo(context, dao);
    }

    @Singleton
    @Provides
    DiscountDao provideDiscountDao(RoomDb db) {
        return db.getDiscountDao();
    }

    @Singleton
    @Provides
    DiscountRepo provideDiscountRepository(Context context, DiscountDao dao) {
        return new DiscountRepo(context, dao);
    }
}
