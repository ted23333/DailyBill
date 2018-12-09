package com.teddong.billforyou.fillforyou.mvp.presenter.Imp;

import com.teddong.billforyou.fillforyou.model.bean.local.BPay;
import com.teddong.billforyou.fillforyou.model.bean.local.BSort;
import com.teddong.billforyou.fillforyou.model.bean.local.NoteBean;
import com.teddong.billforyou.fillforyou.mvp.model.Imp.NoteModelImp;
import com.teddong.billforyou.fillforyou.mvp.model.NoteModel;
import com.teddong.billforyou.fillforyou.mvp.presenter.NotePresenter;
import com.teddong.billforyou.fillforyou.mvp.view.NoteView;

public class NotePresenterImp extends NotePresenter implements NoteModelImp.NoteOnListener{

    private NoteModel model;
    private NoteView view;

    public NotePresenterImp(NoteView view) {
        this.model=new NoteModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BSort bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BPay bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getNote() {
        model.getNote();
    }

    @Override
    public void addSort(BSort bSort) {
        model.addSort(bSort);
    }

    @Override
    public void addPay(BPay bPay) {
        model.addPay(bPay);
    }

    @Override
    public void deleteSort(Long id) {
        model.deleteSort(id);
    }

    @Override
    public void deletePay(Long id) {
        model.deletePay(id);
    }
}
