package com.hngg.jianshi.ui.recommend;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 17:00
 * Author: nedhuo
 * Description:
 */
public class RecommendModel extends BaseModel implements RecommendContract.Model {

    @Inject
    public RecommendModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
