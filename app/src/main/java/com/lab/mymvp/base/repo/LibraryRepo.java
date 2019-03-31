package com.lab.mymvp.base.repo;

import android.content.Context;

import com.lab.mymvp.R;
import com.lab.mymvp.base.dao.LibraryDao;
import com.lab.mymvp.base.entity.Library;

import java.util.List;

public class LibraryRepo {

    private LibraryDao mDao;
    private Context mContext;

    public LibraryRepo(Context context, LibraryDao dao) {
        this.mDao = dao;
        this.mContext = context;
        init();
    }

    private void init() {
        List<Library> libraries = getLibraryItems();
        if (libraries == null || libraries.isEmpty() ) {
            String[] libraryItems = mContext.getResources().getStringArray(R.array.library_items);
            for (String s : libraryItems) {
                Library library = new Library();
                library.setName(s);
                mDao.insert(library);
            }
        }
    }

    public List<Library> getLibraryItems() {
        return mDao.findAll();
    }

}
