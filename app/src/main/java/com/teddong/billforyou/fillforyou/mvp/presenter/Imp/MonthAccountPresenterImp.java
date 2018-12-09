package com.teddong.billforyou.fillforyou.mvp.presenter.Imp;

import com.teddong.billforyou.fillforyou.model.bean.local.MonthAccountBean;
import com.teddong.billforyou.fillforyou.mvp.model.Imp.MonthAccountModelImp;
import com.teddong.billforyou.fillforyou.mvp.model.MonthAccountModel;
import com.teddong.billforyou.fillforyou.mvp.presenter.MonthAccountPresenter;
import com.teddong.billforyou.fillforyou.mvp.view.MonthAccountView;

public class MonthAccountPresenterImp extends MonthAccountPresenter implements MonthAccountModelImp.MonthAccountOnListener{

    private MonthAccountModel model;
    private MonthAccountView view;

    public MonthAccountPresenterImp(MonthAccountView view) {
        this.model=new MonthAccountModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthAccountBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }


    @Override
    public void getMonthAccountBills(int id, String year, String month) {
        model.getMonthAccountBills(id,year,month);
    }
}
