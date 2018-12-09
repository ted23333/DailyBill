package com.teddong.billforyou.fillforyou.mvp.model;

import com.teddong.billforyou.fillforyou.model.bean.remote.MyUser;

public interface UserInfoModel {

    void update(MyUser user);

    void onUnsubscribe();
}
