package com.zkkc.prclient.main.entiy;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zkkc.prclient.main.adapter.HomePopupAdapter;

/**
 * Created by ShiJunRan on 2019/5/6
 */
public class RvDevice implements MultiItemEntity {
    public String deviceName;

    public RvDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public int getItemType() {
        return HomePopupAdapter.TYPE_DEVICE;
    }
}
