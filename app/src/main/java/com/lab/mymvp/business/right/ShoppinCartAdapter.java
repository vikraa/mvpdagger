package com.lab.mymvp.business.right;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.CartItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppinCartAdapter extends BaseQuickAdapter<CartItem> {

    @BindView(R.id.txt_name)
    TextView mName;

    @BindView(R.id.txt_quantity)
    TextView mQuantity;

    @BindView(R.id.txt_price)
    TextView mPrice;

    private Context mContext;

    public ShoppinCartAdapter(Context context, int layoutResId, List<CartItem> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CartItem cartItem) {
        ButterKnife.bind(this, baseViewHolder.itemView);
        mName.setText(cartItem.getItemName());
        mQuantity.setText(cartItem.getQuantity());
        mPrice.setText(String.format("%,d",cartItem.getQuantity()*1));
    }
}
