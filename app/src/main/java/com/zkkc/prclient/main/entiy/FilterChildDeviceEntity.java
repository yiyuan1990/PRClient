package com.zkkc.prclient.main.entiy;

import com.samluys.filtertab.base.BaseFilterBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 * 设备选择子Entity
 */
public class FilterChildDeviceEntity extends BaseFilterBean {
    /**
     * 塔名称
     */
    private String name;
    /**
     * 塔ID
     */
    private int tower_id;
    /**
     * 选择状态
     */
    private int selected;

    public int getTower_id() {
        return tower_id;
    }

    public void setTower_id(int tower_id) {
        this.tower_id = tower_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public int getId() {
        return tower_id;
    }

    @Override
    public int getSelecteStatus() {
        return selected;
    }

    @Override
    public void setSelecteStatus(int status) {
        this.selected = status;
    }

    @Override
    public List getChildList() {
        return null;
    }

    @Override
    public String getSortTitle() {
        return null;
    }

    @Override
    public String getSortKey() {
        return null;
    }

}
