package com.demo.joe.radiorv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                    viewHolder.checked.setVisibility(View.INVISIBLE);
                } else {
                    notifyItemChanged(mSelectedPos);
                }
//                if (mSelectedPos != -1) {
//                    reasons.get(mSelectedPos).setSelected(false);
//                }

                mSelectedPos = position;
//                reasons.get(mSelectedPos).setSelected(true);
                holder.checked.setSelected(true);
                holder.checked.setVisibility(View.VISIBLE);

                if (onItemClickListener != null){
                    onItemClickListener.onItemClickListener(reasons.get(position));
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

        public ReasonViewHolder(final View itemView) {
            super(itemView);
            reason = (TextView) itemView.findViewById(R.id.reason);
            checked = (RadioButton) itemView.findViewById(R.id.checked);
            checked.setVisibility(View.GONE);

        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(ReportBean reportBean);
    }
}
