package com.zkkc.prclient.main.callback;


import com.zkkc.prclient.main.entiy.MediaListBean;

/**
 * Created by ShiJunRan on 2019/5/6
 */
public interface IQueryMediaList {
    void onSuccess(MediaListBean b);

    void onFailure(String strErr);
}
