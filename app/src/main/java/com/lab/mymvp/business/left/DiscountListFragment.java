package com.lab.mymvp.business.left;

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
import com.lab.mymvp.base.repo.DiscountRepo;

import javax.inject.Inject;

import butterknife.BindView;

public class DiscountListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    DiscountRepo mRepo;

    @Inject
    public DiscountListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DiscountAdapter discountAdapter = new DiscountAdapter(getActivity(), R.layout.item_discounts, mRepo.getAllDiscounts());
        mRecyclerView.setAdapter(discountAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
