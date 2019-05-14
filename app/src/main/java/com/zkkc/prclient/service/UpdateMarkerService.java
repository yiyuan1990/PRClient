package com.zkkc.prclient.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.zkkc.prclient.PRClientApp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ShiJunRan on 2019/5/14
 */
public class UpdateMarkerService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public UpdateMarkerService() {
        super("UpdateMarkerService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        LogUtils.d("UpdateMarkerService", "onHandleIntent" + Thread.currentThread().getName());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 打印当前线程的id
                LogUtils.d("UpdateMarkerService", "Thread id is " + Thread.currentThread().getName());
            }
        }, 2000, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("UpdateMarkerService", "onDestroy");
    }
}
