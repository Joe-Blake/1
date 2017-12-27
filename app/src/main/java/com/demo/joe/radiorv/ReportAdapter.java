package com.demo.joe.radiorv;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by weizijie on 2017/7/18.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReasonViewHolder> {


    private List<ReportBean> reasons;
    private OnItemClickListener onItemClickListener;
    private RecyclerView mRv;

    private int mSelectedPos = -1;


    public ReportAdapter(List<ReportBean> reasons,RecyclerView rv) {
        this.reasons = reasons;
        mRv = rv;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }

    @Override
    public ReasonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report,
                parent, false);
        return new ReasonViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReasonViewHolder holder, final int position) {

        holder.reason.setText(reasons.get(position).getReason());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReasonViewHolder viewHolder = (ReasonViewHolder) mRv.findViewHolderForLayoutPosition
                        (mSelectedPos);
                if (viewHolder != null) {
                    viewHolder.img.setImageResource(R.mipmap.t);
                } else {
                    notifyItemChanged(mSelectedPos);
                }
                if (!reasons.get(position).isCheck()) {
                    if (mSelectedPos != -1) {
                        reasons.get(mSelectedPos).setCheck(false);
                    }
                    reasons.get(position).setCheck(true);
                    mSelectedPos = position;
                    holder.img.setImageResource(R.mipmap.ring);
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClickListener(reasons.get(position));
                    }
                } else {
                    reasons.get(mSelectedPos).setCheck(true);
                    mSelectedPos = position;
                    reasons.get(position).setCheck(false);
                    holder.img.setImageResource(R.mipmap.t);
                    if (onItemClickListener != null){
                        onItemClickListener.noChecked();
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return reasons.size();
    }


    public class ReasonViewHolder extends RecyclerView.ViewHolder{

        private TextView reason;
        private RadioButton checked;
        private ImageView img;
        private boolean flag;

        public ReasonViewHolder(final View itemView) {
            super(itemView);
            reason = (TextView) itemView.findViewById(R.id.reason);
            img = (ImageView) itemView.findViewById(R.id.ring);
            flag = false;

        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(ReportBean reportBean);

        void noChecked();
    }
}
