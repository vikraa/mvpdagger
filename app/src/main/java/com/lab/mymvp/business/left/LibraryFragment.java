package com.lab.mymvp.business.left;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.mymvp.R;
import com.lab.mymvp.base.MainContract;
import com.lab.mymvp.base.RoomDb;
import com.lab.mymvp.base.repo.ItemRepo;
import com.lab.mymvp.base.repo.LibraryRepo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LibraryFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecylerView;

    @Inject
    MainContract.Presenter mPresenter;

    @Inject
    RoomDb mDatabase;

    @Inject
    LibraryRepo mLibraryRepo;

    @Inject
    ItemRepo mItemRepo;

    private LibraryAdapter mAdapter;

    @Inject
    public LibraryFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_library, null);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mAdapter = new LibraryAdapter(getActivity(),R.layout.item_library, mLibraryRepo.getLibraryItems());
        mRecylerView.setAdapter(mAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
