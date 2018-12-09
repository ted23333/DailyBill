package com.teddong.billforyou.fillforyou.mvp.presenter.Imp;

import com.teddong.billforyou.fillforyou.model.bean.remote.MyUser;
import com.teddong.billforyou.fillforyou.mvp.model.Imp.UserInfoModelImp;
import com.teddong.billforyou.fillforyou.mvp.model.UserInfoModel;
import com.teddong.billforyou.fillforyou.mvp.presenter.UserInfoPresenter;
import com.teddong.billforyou.fillforyou.mvp.view.UserInfoView;

public class UserInfoPresenterImp extends UserInfoPresenter implements UserInfoModelImp.UserInfoOnListener {

    private UserInfoModel model;
    private UserInfoView view;

    public UserInfoPresenterImp(UserInfoView view) {
        this.model=new UserInfoModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MyUser user) {
        view.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void update(MyUser user) {
        model.update(user);
    }
}
