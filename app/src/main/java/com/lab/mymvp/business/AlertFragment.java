package com.lab.mymvp.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.CartItem;
import com.lab.mymvp.base.entity.Discount;
import com.lab.mymvp.base.entity.ItemData;
import com.lab.mymvp.base.repo.DiscountRepo;
import com.lab.mymvp.business.left.ItemAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lab.mymvp.business.left.ItemAdapter.ITEM_CART_ADDED;

public class AlertFragment extends DialogFragment {

    @BindView(R.id.txt_title)
    TextView mItemName;
    @BindView(R.id.btn_cancel)
    Button mBtnCancel;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    @BindView(R.id.edt_quantity)
    EditText mEdtQuantity;
    @BindView(R.id.btn_increment)
    Button mBtnIncrement;
    @BindView(R.id.btn_decrement)
    Button mBtnDecrement;
    @BindView(R.id.discount_opt1)
    Switch mDiscountSwitchOpt1;
    @BindView(R.id.discount_opt2)
    Switch mDiscountSwitchOpt2;
    @BindView(R.id.discount_opt3)
    Switch mDiscountSwitchOpt3;
    @BindView(R.id.discount_opt4)
    Switch mDiscountSwitchOpt4;
    @Inject
    DiscountRepo mDiscountRepo;

    private ItemData mData;
    private HashMap<Integer, Switch> mDiscountSwitchMap;
    private HashMap<Integer,Discount> mDiscountModelMap;
    private CompoundButton.OnCheckedChangeListener mDiscountSwitchCheckedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            updateState(buttonView.getId(), isChecked);
        }
    };
    @Inject
    public AlertFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.alertdialog_add_item_cart, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mData = getArguments().getParcelable(ItemAdapter.ITEM_DATA_CART);
        if (mData != null) {
            initCartItem();
        }
        mEdtQuantity.setText(String.valueOf(1));
        initDiscount();
    }

    private void initDiscount() {
        mDiscountSwitchMap = new HashMap<>();
        mDiscountSwitchMap.put(mDiscountSwitchOpt1.getId(), mDiscountSwitchOpt1);
        mDiscountSwitchMap.put(mDiscountSwitchOpt2.getId(), mDiscountSwitchOpt2);
        mDiscountSwitchMap.put(mDiscountSwitchOpt3.getId(), mDiscountSwitchOpt3);
        mDiscountSwitchMap.put(mDiscountSwitchOpt4.getId(), mDiscountSwitchOpt4);
        int index = 0;
        List<Discount> discountList = mDiscountRepo.getAllDiscounts();
        mDiscountModelMap = new HashMap<>();
        for (final Map.Entry<Integer, Switch> e : mDiscountSwitchMap.entrySet()) {
            e.getValue().setChecked(false);
            e.getValue().setOnCheckedChangeListener(mDiscountSwitchCheckedListener);
            Discount d = discountList.get(index);
            e.getValue().setText(d.getName().concat(" (").concat(String.valueOf(d.getDiscountValue())).concat(")"));
            mDiscountModelMap.put(e.getKey(), discountList.get(index));
            index++;
        }
    }

    private synchronized void updateState(int id, boolean checked) {
        for (Map.Entry<Integer, Switch> e : mDiscountSwitchMap.entrySet()) {
            e.getValue().setOnCheckedChangeListener(null);
            if (e.getValue().getId() == id) {
                e.getValue().setChecked(checked);
            } else {
                e.getValue().setChecked(false);
            }
            e.getValue().setOnCheckedChangeListener(mDiscountSwitchCheckedListener);
        }
    }

    private float getDiscount() {
        for (Map.Entry<Integer, Switch> e : mDiscountSwitchMap.entrySet()) {
            if (e.getValue().isChecked()) {
                Log.d("DISCOUNT", String.valueOf(mDiscountModelMap.get(e.getKey()).getDiscountValue()));
                return mDiscountModelMap.get(e.getKey()).getDiscountValue();
            }
        }
        return 0;
    }

    private void initCartItem() {
        mItemName.setText(mData.getTitle());
        mEdtQuantity.setText(String.valueOf(1));
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem cartItem = new CartItem();
                cartItem.setItemName(mData.getTitle());
                cartItem.setDiscountValue(getDiscount());
                cartItem.setQuantity(Integer.valueOf(mEdtQuantity.getText().toString()));
                cartItem.setPrice(cartItem.getQuantity() * mData.getPrice());
                Bundle bundle = new Bundle();
                bundle.putParcelable(ITEM_CART_ADDED, cartItem);
                Intent itemCart = new Intent(ITEM_CART_ADDED);
                itemCart.putExtra(ITEM_CART_ADDED, bundle);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(itemCart);
                dismissAllowingStateLoss();
            }
        });
        mBtnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.valueOf(mEdtQuantity.getText().toString());
                if (value < 1000) {
                    value++;
                    AlertFragment.this.mEdtQuantity.setText(String.valueOf(value));
                }
            }
        });
        mBtnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.valueOf(mEdtQuantity.getText().toString());
                if (value > 1) {
                    value--;
                    mEdtQuantity.setText(String.valueOf(value));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams)params);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment != null && fragment.isVisible()) {
            return;
        }

        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();

        if (mEdtQuantity != null) {
            mEdtQuantity.setText(String.valueOf(1));
            initDiscount();
        }

    }

}
