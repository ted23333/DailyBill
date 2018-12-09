package com.teddong.billforyou.fillforyou.mvp.presenter;

import com.teddong.billforyou.fillforyou.base.BasePresenter;
import com.teddong.billforyou.fillforyou.model.bean.local.BBill;

public abstract  class MonthDetailPresenter extends BasePresenter {

    public abstract void getMonthDetailBills(int id,String year,String month);

    public abstract void deleteBill(Long id);

    public abstract void updateBill(BBill bBill);
}
