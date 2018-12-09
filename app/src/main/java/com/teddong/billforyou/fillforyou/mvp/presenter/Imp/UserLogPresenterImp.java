package com.teddong.billforyou.fillforyou.mvp.presenter.Imp;

import com.teddong.billforyou.fillforyou.model.bean.remote.MyUser;
import com.teddong.billforyou.fillforyou.mvp.model.Imp.UserLogModelImp;
import com.teddong.billforyou.fillforyou.mvp.model.UserLogModel;
import com.teddong.billforyou.fillforyou.mvp.presenter.UserLogPresenter;
import com.teddong.billforyou.fillforyou.mvp.view.UserLogView;

public class UserLogPresenterImp extends UserLogPresenter implements UserLogModelImp.UserLogOnListener{

    private UserLogModel userLogModel;
    private UserLogView userLogView;

    public UserLogPresenterImp(UserLogView userLogView) {
        this.userLogModel=new UserLogModelImp(this);
        this.userLogView = userLogView;
    }

    @Override
    public void onSuccess(MyUser user) {
        userLogView.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        userLogView.loadDataError(e);
    }

    @Override
    public void login(String username,String password) {
        userLogModel.login(username,password);
    }

    @Override
    public void signup(String username,String password,String mail) {
        userLogModel.signup(username,password,mail);
    }
}
