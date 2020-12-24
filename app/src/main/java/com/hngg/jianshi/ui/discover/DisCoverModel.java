package com.hngg.jianshi.ui.discover;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:49
 * Author: nedhuo
 * Description:
 */
public class DisCoverModel extends BaseModel implements DisCoverContract.Model {

    @Inject
    public DisCoverModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
