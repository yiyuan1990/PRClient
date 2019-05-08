package com.zkkc.prclient.main.entiy;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zkkc.prclient.main.adapter.HomePopupAdapter;

/**
 * Created by ShiJunRan on 2019/5/6
 */
public class RvLine extends AbstractExpandableItem<RvDevice> implements MultiItemEntity {
    public String lineName;
    public String lineColor;

    public RvLine(String lineName, String lineColor) {
        this.lineName = lineName;
        this.lineColor = lineColor;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return HomePopupAdapter.TYPE_LINE;
    }
}
