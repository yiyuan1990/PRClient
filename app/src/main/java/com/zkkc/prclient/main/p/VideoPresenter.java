package com.zkkc.prclient.main.p;

import android.content.Context;

import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.contract.VideoContract;
import com.zkkc.prclient.main.m.HomeModel;
import com.zkkc.prclient.main.m.VideoModel;

import java.util.concurrent.ExecutorService;

/**
 * Created by ShiJunRan on 2019/5/6
 * 视频Presenter
 */
public class VideoPresenter extends VideoContract.Presenter {
    private Context mContext;
    private ExecutorService threadPool;
    private VideoModel model;

    public VideoPresenter(Context mContext) {
        this.mContext = mContext;
        model = new VideoModel();
        threadPool = ThreadPoolHelp.Builder
                .cached()
                .builder();
    }


}
