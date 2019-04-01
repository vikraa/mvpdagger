package com.lab.mymvp.business.left;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lab.mymvp.R;
import com.lab.mymvp.base.entity.Library;
import com.lab.mymvp.business.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LibraryAdapter extends BaseQuickAdapter<Library> {

    @BindView(R.id.txt_library_name)
    TextView mTxtName;

    private Context mContext;

    public LibraryAdapter(Context context, int layoutResId, List<Library> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final Library library) {
        ButterKnife.bind(this, baseViewHolder.itemView);
        mTxtName.setText(library.getName());
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseViewHolder.getAdapterPosition() == 0) { //discount
                    ((MainActivity)mContext).showFragmentDiscounts();
                } else { //allitems
                    ((MainActivity)mContext).showFragmentAllItems();
                }
            }
        });
    }
}
