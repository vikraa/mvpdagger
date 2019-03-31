package com.lab.mymvp.base;

import java.util.List;

public interface MainContract {

    interface View<T> {
        void showData(List<T> list);
    }

    interface Presenter {
        void initData();
        void unsubscribe();
    }
}
