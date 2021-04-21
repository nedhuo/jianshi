package com.hngg.jianshi.ui.tag;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
class TagDetailModel extends BaseModel implements TagDetailContract.Model {
    @Inject
    public TagDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
