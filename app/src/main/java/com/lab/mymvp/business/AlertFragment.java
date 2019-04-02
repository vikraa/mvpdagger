package com.lab.mymvp.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.ItemData;
import com.lab.mymvp.business.left.ItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ItemData data = getArguments().getParcelable(ItemAdapter.ITEM_DATA_CART);
        if (data != null) {
            initCartItem(data);
        }
    }

    private void initCartItem(ItemData data) {
        mItemName.setText(data.getTitle());
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
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(ItemAdapter.ITEM_CART_ADDED));
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
    }

}
