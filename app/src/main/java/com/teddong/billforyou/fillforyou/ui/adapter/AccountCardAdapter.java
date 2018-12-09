package com.teddong.billforyou.fillforyou.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teddong.billforyou.fillforyou.R;
import com.teddong.billforyou.fillforyou.model.bean.local.MonthAccountBean;
import com.teddong.billforyou.fillforyou.common.Constants;
import com.teddong.billforyou.fillforyou.utils.ImageUtils;

import java.util.List;

/**
 * 卡片布局Adapter
 */
public class AccountCardAdapter extends RecyclerView.Adapter<AccountCardAdapter.ViewHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MonthAccountBean.PayTypeListBean> mDatas;


    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }



    public void setmDatas(List<MonthAccountBean.PayTypeListBean> mDatas) {
        this.mDatas = mDatas;
    }

    public AccountCardAdapter(Context context, List<MonthAccountBean.PayTypeListBean> datas){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this. mDatas = datas;

    }


    @Override
    public int getItemCount() {
        return (mDatas== null) ? 0 : mDatas.size();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_tally_account, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.money_out.setText("-"+mDatas.get(position).getOutcome());
        holder.money_in.setText("+"+mDatas.get(position).getIncome());
        holder.title.setText(mDatas.get(position).getPayName());
        holder.img.setImageDrawable(ImageUtils.getDrawable(mDatas.get(position).getPayImg()));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView code;
        private TextView money_out;
        private TextView money_in;
        private ImageView img;

        public ViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            code = (TextView) view.findViewById(R.id.code);
            money_out = (TextView) view.findViewById(R.id.money_out);
            money_in = (TextView) view.findViewById(R.id.money_in);
            img = (ImageView) view.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(mListener != null){
                mListener.onItemClick(getAdapterPosition());
            }
        }

    }

}
