package com.zkkc.prclient.main.p;

import android.content.Context;

import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.contract.PicContract;
import com.zkkc.prclient.main.m.HomeModel;
import com.zkkc.prclient.main.m.PicModel;

import java.util.concurrent.ExecutorService;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片Presenter
 */
public class PicPresenter extends PicContract.Presenter {
    private Context mContext;
    private PicModel model;

    public PicPresenter(Context mContext) {
        this.mContext = mContext;
        model = new PicModel();

    }


}
