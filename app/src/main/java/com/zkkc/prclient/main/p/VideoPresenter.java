package com.zkkc.prclient.main.p;

import android.content.Context;

import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.prclient.main.callback.IQueryMediaList;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.contract.VideoContract;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.m.HomeModel;
import com.zkkc.prclient.main.m.PicModel;
import com.zkkc.prclient.main.m.VideoModel;

import java.util.concurrent.ExecutorService;

/**
 * Created by ShiJunRan on 2019/5/6
 * 视频Presenter
 */
public class VideoPresenter extends VideoContract.Presenter {
    private Context mContext;
    private VideoModel model;
    private PicModel picModel;

    public VideoPresenter(Context mContext) {
        this.mContext = mContext;
        model = new VideoModel();
        picModel = new PicModel();
    }


    @Override
    public void queryMediaList(String deviceName, String lineName) {
        picModel.queryMediaList(deviceName, lineName, new IQueryMediaList() {
            @Override
            public void onSuccess(MediaListBean b) {
                getView().queryMediaListSuccess(b);
            }

            @Override
            public void onFailure(String strErr) {
                getView().queryMediaListFailure(strErr);
            }
        });

    }
}
