package com.teddong.billforyou.fillforyou.mvp.view;

import com.teddong.billforyou.fillforyou.base.BaseView;
import com.teddong.billforyou.fillforyou.model.bean.BaseBean;
import com.teddong.billforyou.fillforyou.model.bean.local.MonthDetailBean;

public interface MonthDetailView extends BaseView<MonthDetailBean>{

    /**
     * 本地当月账单
     * @param list
     */
    void loadDataSuccess(MonthDetailBean list);
    /**
     * 删除成功
     * @param tData
     */
    void loadDataSuccess(BaseBean tData);
}
