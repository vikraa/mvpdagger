package com.lab.mymvp.business.right;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab.mymvp.R;
import com.lab.mymvp.business.left.ItemAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private LocalBroadcastManager mBroadcastManager;

    private BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("RECEIVER", "received intent");
        }
    };

    @Inject
    public ShoppingCartFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        initBroadcastReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoppingcart, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    private void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ItemAdapter.ITEM_CART_ADDED);
        mBroadcastManager.registerReceiver(mBroadCastReceiver, filter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBroadcastManager.unregisterReceiver(mBroadCastReceiver);
    }
}
