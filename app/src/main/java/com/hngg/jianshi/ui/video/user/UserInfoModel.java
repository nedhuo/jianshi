package com.hngg.jianshi.ui.video.user;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 */
public class UserInfoModel extends BaseModel implements UserInfoContract.Model {
    @Inject
    public UserInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
