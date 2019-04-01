package com.lab.mymvp.business.left;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.Discount;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscountAdapter extends BaseQuickAdapter<Discount> {
    @BindView(R.id.txt_name)
    TextView mTxtName;

    @BindView(R.id.txt_value)
    TextView mTxtValue;

    private Context mContext;

    public DiscountAdapter(Context context, int layoutResId, List<Discount> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Discount discount) {
        ButterKnife.bind(this, baseViewHolder.itemView);
        mTxtName.setText(discount.getName());
        mTxtValue.setText(String.valueOf(discount.getDiscountValue()).concat(" %"));
    }
}
