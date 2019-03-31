package com.lab.mymvp.base.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.lab.mymvp.base.entity.Library;

import java.util.List;

@Dao
public interface LibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Library library);

    @Query("select * from Library")
    List<Library> findAll();

}
