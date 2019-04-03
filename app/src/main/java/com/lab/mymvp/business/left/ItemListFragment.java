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
import com.lab.mymvp.base.repo.ItemRepo;
import com.lab.mymvp.business.AlertFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    ItemRepo mItemRepo;

    @Inject
    AlertFragment mAddItemCartDialog;

    private ItemAdapter mItemAdapter;

    @Inject
    public ItemListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_items, null);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mItemAdapter = new ItemAdapter(getActivity(),R.layout.item_payload, mItemRepo.getAllItems());
        mItemAdapter.setShoppingItemCartDialog(mAddItemCartDialog);
        mRecyclerView.setAdapter(mItemAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
