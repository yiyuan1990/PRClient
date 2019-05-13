package com.zkkc.prclient.main.entiy;

import com.baidu.mapapi.map.Overlay;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/13
 */
public class AllShowMarkerBean {
    private int mPos;
    private List<Overlay> overlays;

    public int getmPos() {
        return mPos;
    }

    public void setmPos(int mPos) {
        this.mPos = mPos;
    }

    public List<Overlay> getOverlays() {
        return overlays;
    }

    public void setOverlays(List<Overlay> overlays) {
        this.overlays = overlays;
    }
}
