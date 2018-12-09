package com.teddong.billforyou.fillforyou.mvp.presenter.Imp;

import com.teddong.billforyou.fillforyou.model.bean.local.MonthChartBean;
import com.teddong.billforyou.fillforyou.mvp.model.Imp.MonthChartModelImp;
import com.teddong.billforyou.fillforyou.mvp.model.MonthChartModel;
import com.teddong.billforyou.fillforyou.mvp.presenter.MonthChartPresenter;
import com.teddong.billforyou.fillforyou.mvp.view.MonthChartView;

public class MonthChartPresenterImp extends MonthChartPresenter implements MonthChartModelImp.MonthChartOnListener{

    private MonthChartModel model;
    private MonthChartView view;

    public MonthChartPresenterImp(MonthChartView view) {
        this.model=new MonthChartModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthChartBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getMonthChartBills(int id, String year, String month) {
        model.getMonthChartBills(id,year,month);
    }

}
