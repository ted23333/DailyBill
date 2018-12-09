package com.teddong.billforyou.fillforyou.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.teddong.billforyou.fillforyou.R;
import com.teddong.billforyou.fillforyou.model.bean.local.BPay;
import com.teddong.billforyou.fillforyou.ui.adapter.SortEditAdapter;
import com.teddong.billforyou.fillforyou.model.bean.local.BSort;
import com.teddong.billforyou.fillforyou.model.bean.local.NoteBean;
import com.teddong.billforyou.fillforyou.mvp.presenter.Imp.NotePresenterImp;
import com.teddong.billforyou.fillforyou.mvp.presenter.NotePresenter;
import com.teddong.billforyou.fillforyou.utils.*;
import com.teddong.billforyou.fillforyou.mvp.view.NoteView;

import java.util.Collections;
import java.util.List;

/**
 * SortEditActivity
 */
public class SortEditActivity extends BaseActivity implements NoteView{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tb_note_income)
    TextView incomeTv;    //收入按钮
    @BindView(R.id.tb_note_outcome)
    TextView outcomeTv;   //支出按钮

    private AlertDialog alertDialog;

    private NotePresenter presenter;

    public boolean isOutcome = true;

    private SortEditAdapter sortEditAdapter;

    private NoteBean noteBean;
    private List<BSort> mDatas;

    @Override
    protected int getLayout() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void initEventAndData() {

        Intent intent=getIntent();
        isOutcome=intent.getBooleanExtra("type",true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        presenter=new NotePresenterImp(this);

        noteBean= SharedPUtils.getUserNoteBean(this);
        //本地获取失败后
        if (noteBean==null){
            //同步获取分类、支付方式信息
            presenter.getNote();
        }else {
            //成功后加载布局
            setTitleStatus();
        }
    }

    @Override
    public void loadDataSuccess(BSort tData) {
        ProgressUtils.dismiss();
    }

    @Override
    public void loadDataSuccess(BPay tData) {
        ProgressUtils.dismiss();
    }

    @Override
    public void loadDataSuccess(NoteBean tData) {
        noteBean=tData;
        //成功后加载布局
        setTitleStatus();
        //保存数据
        SharedPUtils.setUserNoteBean(mContext,tData);
    }

    @Override
    public void loadDataError(Throwable throwable) {
        ProgressUtils.dismiss();
        SnackbarUtils.show(mContext,throwable.getMessage());
    }

    /**
     * 设置状态
     */
    private void setTitleStatus() {

        if (isOutcome){
            //设置支付状态
            outcomeTv.setSelected(true);
            incomeTv.setSelected(false);
            mDatas = noteBean.getOutSortlis();
        }else{
            //设置收入状态
            incomeTv.setSelected(true);
            outcomeTv.setSelected(false);
            mDatas = noteBean.getInSortlis();
        }

        initView();

    }

    /**
     * 加载数据
     */
    private void initView(){
        sortEditAdapter=new SortEditAdapter(this,mDatas);
        mRecyclerView.setAdapter(sortEditAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
                int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
                int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//侧滑删除
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(mDatas,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                sortEditAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                saveEdit();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
                showDeteteDialog(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;
            }
        });

        helper.attachToRecyclerView(mRecyclerView);
    }
    /**
     * 保存修改
     */
    public void saveEdit(){
        if (isOutcome){
            noteBean.setOutSortlis(mDatas);
        }else{
            noteBean.setInSortlis(mDatas);
        }
        SharedPUtils.setUserNoteBean(SortEditActivity.this,noteBean);
    }

    /**
     * 监听点击事件
     * @param view
     */
    @OnClick({R.id.tb_note_income, R.id.tb_note_outcome, R.id.back_btn,R.id.add_btn})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:  //返回
                setResult(RESULT_OK, new Intent());
                finish();
                break;
            case R.id.add_btn: //添加
                showContentDialog();
                break;
            case R.id.tb_note_income://收入
                isOutcome = false;
                setTitleStatus();
                break;
            case R.id.tb_note_outcome://支出
                isOutcome = true;
                setTitleStatus();
                break;
        }
    }

    /**
     * 显示备注内容输入框
     */
    public void showContentDialog() {
        final EditText editText = new EditText(SortEditActivity.this);

        //弹出输入框
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("输入名称")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = editText.getText().toString();
                        if (input.equals("")) {
                            SnackbarUtils.show(mContext, "内容不能为空！");
                        } else {
//                            ProgressUtils.show(mContext);
//                            presenter.addSort(currentUser.getId(), input, "sort_tianjiade.png", !isOutcome);
                            BSort sort=new BSort(null,input, "sort_tianjiade.png",0, !isOutcome);
                            presenter.addSort(sort);
                            mDatas.add(sort);
                            saveEdit();
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }


    /**
     * 删除提示对话框
     */
    public void showDeteteDialog(final int index) {
        //弹出输入框
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("确定删除此分类")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mDatas.remove(index);
                        sortEditAdapter.notifyItemRemoved(index);
                        saveEdit();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sortEditAdapter.notifyDataSetChanged();
                    }
                })
                .show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
            setResult(RESULT_OK, new Intent());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
