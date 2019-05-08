package com.zkkc.prclient.main.adapter;

import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.RvDevice;
import com.zkkc.prclient.main.entiy.RvLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/6
 */
public class HomePopupAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LINE = 0;
    public static final int TYPE_DEVICE = 1;
    private int[] colors = {R.mipmap.line_a, R.mipmap.line_b, R.mipmap.line_c, R.mipmap.line_d, R.mipmap.line_e};

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomePopupAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LINE, R.layout.item_home_popup);
        addItemType(TYPE_DEVICE, R.layout.item_home_popup_a);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LINE:
                final RvLine rvLine = (RvLine) item;
                helper.setText(R.id.tvLineName, rvLine.lineName)
                        .setImageResource(R.id.ivMark, rvLine.isExpanded() ? R.mipmap.icon_down : R.mipmap.icon_up);
                final ImageView ivLine = helper.getView(R.id.ivLine);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (rvLine.isExpanded()) {
                            collapse(pos);

                        } else {
                            expand(pos);

                        }

                    }
                });

                break;
            case TYPE_DEVICE:
                final RvDevice rvDevice = (RvDevice) item;
                helper.setText(R.id.tvDeviceName, rvDevice.deviceName);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(rvDevice.deviceName);
                    }
                });

                break;
        }

    }
}
