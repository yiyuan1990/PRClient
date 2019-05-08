package com.zkkc.prclient.main.entiy;

import com.samluys.filtertab.base.BaseFilterBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 * 线路选择父Entity
 */
public class FilterLineEntity extends BaseFilterBean {

    /**
     * 线路名称
     */
    private String name;
    /**
     * 线路对应的ID
     */
    private int line_id;
    /**
     * 选择状态 0 选择 1 选择
     */
    private int selected;
    /**
     * 二级分类数据
     */
    private List<FilterChildLineEntity> childLines;

    public List<FilterChildLineEntity> getChildLines() {
        return childLines;
    }

    public void setChildLines(List<FilterChildLineEntity> childLines) {
        this.childLines = childLines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
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
        return line_id;
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
    public String getSortTitle() {
        return null;
    }

    @Override
    public List getChildList() {
        return childLines;
    }

    @Override
    public String getSortKey() {
        return null;
    }
}
