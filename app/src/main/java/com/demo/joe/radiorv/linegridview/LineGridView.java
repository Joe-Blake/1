package com.demo.joe.radiorv.linegridview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.utils.DisplayUtil;

/**
 * Created by weizijie on 2017/12/28.
 */

public class LineGridView extends GridView {

    private static int paintWidth = 1;

    private Context mContext;
    private int rownum;
    private int mOffset = -1;


    public LineGridView(Context context) {
        super(context);
        mOffset = DisplayUtil.dip2px(context, 12);
        Log.i("zj", "1");
    }

    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mOffset = DisplayUtil.dip2px(context, 12);
        Log.i("zj", "2");

    }

    public LineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mOffset = DisplayUtil.dip2px(context, 12);
        Log.i("zj", "3");

    }

    public void init(Context context, int offset) {
        mContext = context;
        mOffset = DisplayUtil.dip2px(mContext, offset);
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int colnum = getNumColumns(); //获取列数
        int total = getChildCount();  //获取Item总数

        if (total % colnum == 0) {
            rownum = total / colnum;
        } else {
            rownum = (total / colnum) + 1; //当余数不为0时，要把结果加上1
        }
        Paint localPaint; //设置画笔
        localPaint = new Paint();
        localPaint.setStrokeWidth(DisplayUtil.dip2px(mContext, paintWidth));
        localPaint.setStyle(Paint.Style.STROKE); //画笔实心
        localPaint.setColor(getContext().getResources().getColor(R.color.grid_line));//画笔颜色

//        View view0 = getChildAt(0); //第一个view
//        View viewColLast = getChildAt(colnum - 1);//第一行最后一个view
//        View viewRowLast = getChildAt((rownum - 1) * colnum); //第一列最后一个view
//        for (int i = 1, c = 1; i < rownum || c < colnum; i++, c++) {
        //横线
//            canvas.drawLine(view0.getLeft(), view0.getBottom() * i, viewColLast.getRight(),
// viewColLast.getBottom() * i, localPaint);
        //竖线
//            canvas.drawLine(view0.getRight() * c, view0.getTop() + offset,
//                    viewRowLast.getRight() * c, viewRowLast.getBottom() - offset, localPaint);
//        }
        Log.i("zj", mOffset + "");
        if (mOffset >= 0) {
            for (int i = 0; i < rownum * colnum; i++) {
                if (i >= getChildCount()) {
                    break;
                }
                View view = getChildAt(i);
                canvas.drawLine(view.getRight(), view.getTop() + mOffset,
                        view.getRight(), view.getBottom() - mOffset, localPaint);

            }
        }

    }
}