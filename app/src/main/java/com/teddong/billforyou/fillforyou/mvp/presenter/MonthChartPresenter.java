package com.teddong.billforyou.fillforyou.mvp.presenter;

import com.teddong.billforyou.fillforyou.base.BasePresenter;

public abstract  class MonthChartPresenter extends BasePresenter {

    public abstract void getMonthChartBills(int id,String year,String month);
}
