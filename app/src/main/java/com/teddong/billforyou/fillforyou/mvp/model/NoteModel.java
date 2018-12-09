package com.teddong.billforyou.fillforyou.mvp.model;

import com.teddong.billforyou.fillforyou.model.bean.local.BPay;
import com.teddong.billforyou.fillforyou.model.bean.local.BSort;

public interface NoteModel {

    void getNote();

    void addSort(BSort bSort);

    void addPay(BPay bPay);

    void deleteSort(Long id);

    void deletePay(Long id);

    void onUnsubscribe();
}
