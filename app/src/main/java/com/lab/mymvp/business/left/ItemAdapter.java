package com.lab.mymvp.business.left;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.ItemData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends BaseQuickAdapter<ItemData> {

    @BindView(R.id.img_item)
    ImageView mImgItem;

    @BindView(R.id.txt_item)
    TextView mTxtItem;
    private Context mContext;

    public ItemAdapter(Context context, int layoutResId, List<ItemData> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ItemData item) {
        ButterKnife.bind(this, baseViewHolder.itemView);
        Picasso.with(mContext).load(item.getThumbNailUrl())
                .error(R.drawable.ic_launcher_background)
                .noFade()
                .into(mImgItem);
        mTxtItem.setText(item.getTitle());
    }
}
