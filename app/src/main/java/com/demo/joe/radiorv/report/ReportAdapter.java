package com.demo.joe.radiorv.report;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.utils.CommonStringUtils;

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

    public ReportAdapter(RecyclerView rv) {
        mRv = rv;
    }

    public void setReasons(List<ReportBean> reasons) {
        this.reasons = reasons;
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

            String a = "2D0081";
            String b = "8B3097";
            String c = "D14E7A";

//            int colors[] = { 0xff2D0081 , 0xff8B3097, 0xffD14E7A };
            int colors[] = {CommonStringUtils.parseColor(a), CommonStringUtils.parseColor(b), CommonStringUtils
                    .parseColor(c)};

            GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
            int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                reason.setBackgroundDrawable(bg);
            } else {
                reason.setBackground(bg);
            }


        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(ReportBean reportBean);

        void noChecked();
    }
}
