package com.hngg.jianshi.ui.me.download.downloading;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class DownloadingModel extends BaseModel {
    @Inject
    public DownloadingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
