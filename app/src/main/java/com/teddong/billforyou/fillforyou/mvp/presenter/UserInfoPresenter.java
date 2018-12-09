package com.teddong.billforyou.fillforyou.mvp.presenter;

import com.teddong.billforyou.fillforyou.base.BasePresenter;
import com.teddong.billforyou.fillforyou.model.bean.remote.MyUser;

public abstract  class UserInfoPresenter extends BasePresenter {

    public abstract void update(MyUser user);
}
