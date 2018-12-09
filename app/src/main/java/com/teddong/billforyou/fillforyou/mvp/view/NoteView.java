package com.teddong.billforyou.fillforyou.mvp.view;

import com.teddong.billforyou.fillforyou.base.BaseView;
import com.teddong.billforyou.fillforyou.model.bean.local.BPay;
import com.teddong.billforyou.fillforyou.model.bean.local.BSort;
import com.teddong.billforyou.fillforyou.model.bean.local.NoteBean;

public interface NoteView extends BaseView<NoteBean>{

    /**
     * 请求数据成功
     * @param tData
     */
    void loadDataSuccess(BSort tData);
    void loadDataSuccess(BPay tData);
}
