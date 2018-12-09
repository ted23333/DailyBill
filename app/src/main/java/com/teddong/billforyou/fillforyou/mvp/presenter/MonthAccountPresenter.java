package com.teddong.billforyou.fillforyou.mvp.presenter;

import com.teddong.billforyou.fillforyou.base.BasePresenter;

public abstract  class MonthAccountPresenter extends BasePresenter {

    public abstract void getMonthAccountBills(int id,String year,String month);
}
