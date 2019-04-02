package com.lab.mymvp.business.left;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.ItemData;
import com.lab.mymvp.business.AlertFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends BaseQuickAdapter<ItemData> {

    @BindView(R.id.img_item)
    ImageView mImgItem;

    @BindView(R.id.txt_item)
    TextView mTxtItem;

    @BindView(R.id.txt_price)
    TextView mTxtPrice;

    private Context mContext;
    private AlertFragment mAlertCartItem;
    private static final String TAG = "CART_ALERT";
    public static final String ITEM_DATA_CART = "item_data_cart";
    public static final String ITEM_CART_ADDED = "item_cart_added";
    public ItemAdapter(Context context, int layoutResId, List<ItemData> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final ItemData item) {
        ButterKnife.bind(this, baseViewHolder.itemView);
        Picasso.with(mContext).load(item.getThumbNailUrl())
                .error(R.drawable.ic_launcher_background)
                .noFade()
                .into(mImgItem);
        mTxtItem.setText(item.getTitle());
        mTxtPrice.setText(String.format("$ %,d", item.getPrice()));
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddcartItem(item);
            }
        });
    }

    private void showAddcartItem(ItemData data) {
        mAlertCartItem = new AlertFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_DATA_CART, data);
        mAlertCartItem.setArguments(bundle);
        mAlertCartItem.show(((AppCompatActivity)mContext).getSupportFragmentManager(),TAG);
    }
}
