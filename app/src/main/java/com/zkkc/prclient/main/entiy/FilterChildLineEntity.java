package com.zkkc.prclient.main.entiy;

import com.samluys.filtertab.base.BaseFilterBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 * 线路选择子Entity
 */
public class FilterChildLineEntity extends BaseFilterBean {
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备ID
     */
    private int device_id;
    /**
     * 选择状态
     */
    private int selected;
    /**
     * 二级分类数据
     */
    private List<FilterChildDeviceEntity> childDevices;

    public List<FilterChildDeviceEntity> getChildDevices() {
        return childDevices;
    }

    public void setChildDevices(List<FilterChildDeviceEntity> childDevices) {
        this.childDevices = childDevices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
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
        return device_id;
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
        return childDevices;
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
