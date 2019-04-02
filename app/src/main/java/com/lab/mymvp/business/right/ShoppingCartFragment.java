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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.CartItem;
import com.lab.mymvp.base.repo.CartRepo;
import com.lab.mymvp.business.left.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lab.mymvp.business.left.ItemAdapter.ITEM_CART_ADDED;

public class ShoppingCartFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_clear)
    Button mBtnClear;
    @Inject
    CartRepo mCartRepo;

    private LocalBroadcastManager mBroadcastManager;
    private ShoppinCartAdapter mAdapter;
    private float mSubtotal;
    private float mDiscountPrice;
    private float mChargePrice;
    private BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("RECEIVER", "received intent");
            Bundle bundle = intent.getBundleExtra(ITEM_CART_ADDED);
            CartItem item = (CartItem)bundle.getParcelable(ITEM_CART_ADDED);
            if (item != null) {
                long affected = mCartRepo.insert(item);
            }
            List<CartItem> items = mCartRepo.getAllCartItems();
            for (CartItem c : items) {
                mSubtotal += c.getPrice();
                mDiscountPrice += (c.getPrice() - (c.getPrice()*(c.getDiscountValue()/100)));
            }
            mChargePrice = mSubtotal - mDiscountPrice;
            mAdapter.setNewData(mCartRepo.getAllCartItems());
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
        mAdapter = new ShoppinCartAdapter(getActivity(), R.layout.item_cart, mCartRepo.getAllCartItems());
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                Log.d("ONCHANGED","adapter value changed");
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCartRepo.deleteAllItems();
                mAdapter.setNewData(new ArrayList<CartItem>());
            }
        });
    }

    private void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ITEM_CART_ADDED);
        mBroadcastManager.registerReceiver(mBroadCastReceiver, filter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mBroadcastManager.unregisterReceiver(mBroadCastReceiver);
    }
}
