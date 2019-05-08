package com.zkkc.prclient.main.entiy;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 * 筛选数据
 */
public class FilterEntity {
    /**
     * 线路
     */
    private List<FilterLineEntity> lines;

    public List<FilterLineEntity> getLines() {
        return lines;
    }

    public void setLines(List<FilterLineEntity> lines) {
        this.lines = lines;
    }
}
