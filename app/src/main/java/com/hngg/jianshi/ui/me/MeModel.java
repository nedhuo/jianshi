package com.hngg.jianshi.ui.me;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * Date: 2020/11/19
 * Timer: 16:21
 * Author: nedhuo
 * Description:
 */
public class MeModel extends BaseModel implements MeContract.Model {

    @Inject
    public MeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
