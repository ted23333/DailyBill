package com.teddong.billforyou.fillforyou.mvp.view;

import com.teddong.billforyou.fillforyou.base.BaseView;
import com.teddong.billforyou.fillforyou.model.bean.BaseBean;
import com.teddong.billforyou.fillforyou.model.bean.local.NoteBean;

public interface BillView extends BaseView<BaseBean>{

    void loadDataSuccess(NoteBean tData);
}
