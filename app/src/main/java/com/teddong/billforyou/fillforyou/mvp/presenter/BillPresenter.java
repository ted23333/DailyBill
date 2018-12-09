package com.teddong.billforyou.fillforyou.mvp.presenter;

import com.teddong.billforyou.fillforyou.base.BasePresenter;
import com.teddong.billforyou.fillforyou.model.bean.local.BBill;

public abstract  class BillPresenter extends BasePresenter {

    /**
     * 获取信息
     */
    public abstract void getNote();

    /**
     * 添加账单
     */
    public abstract void add(BBill bBill);

    /**
     * 修改账单
     */
    public abstract void update(BBill bBill);


    /**
     * 删除账单
     */
    public abstract void delete(Long id);
}
