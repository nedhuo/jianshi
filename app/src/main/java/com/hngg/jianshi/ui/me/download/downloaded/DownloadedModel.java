package com.hngg.jianshi.ui.me.download.downloaded;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */

public class DownloadedModel extends BaseModel implements DownloadedContract.Model{

    @Inject
    public DownloadedModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {

    }
}
