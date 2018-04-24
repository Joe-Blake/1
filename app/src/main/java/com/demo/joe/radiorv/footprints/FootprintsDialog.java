package com.demo.joe.radiorv.footprints;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.demo.joe.radiorv.MainActivity;
import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import hugo.weaving.DebugLog;

/**
 * Created by weizijie on 2018/2/27.
 */
@DebugLog
public class FootprintsDialog extends Dialog {

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    private Context mContext;
    private MultiViewPager mViewPager;
    private TextView mIndex;
    private TextView more;
    private int count;
    private List<FootprintBean> mFootprintBeanList;


    public FootprintsDialog(@NonNull Context context, List<FootprintBean> footprintBeanList) {
        super(context, R.style.DropDown);
        mContext = context;
        mFootprintBeanList = footprintBeanList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window dialogWindow = getWindow();
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.footprint_dialog, null);
        setContentView(rootView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        more = (TextView) rootView.findViewById(R.id.more);
        mIndex = (TextView) rootView.findViewById(R.id.index);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                final Intent intent = new Intent(mContext, MainActivity.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.startActivity(intent);
                    }
                },500);

            }
        });
        assert dialogWindow != null;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;
        lp.height = DisplayUtil.dip2px(mContext, 300);
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.TOP);
        dialogWindow.setWindowAnimations(R.style.DropDown);

        mViewPager = (MultiViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setClipChildren(false);

        List<View> itemViews = new ArrayList<>();
        for (int i = 0; i < mFootprintBeanList.size(); i++) {
            PagerItem item = new PagerItem(mContext, i, mFootprintBeanList);
            itemViews.add(item);
        }
        count = itemViews.size();
        mIndex.setText(String.format("我的足迹(1/%d)", count));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == count - 1) {
                    more.setVisibility(View.VISIBLE);
                } else {
                    more.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mIndex.setText(String.format("我的足迹(%d/%d)", (position + 1), count));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        DialogAdapter adapter = new DialogAdapter(itemViews);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(false, new FootprintTransformer(0.5f, 0.7f));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        float x1 = 0;
        float x2 = 0;
        float y1 = 0;
        float y2 = 0;
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            x2 = event.getX();
            y2 = event.getY();
            if (y1 - y2 > Math.abs(x1 - x2) && (y1 - y2) > 100) {
                dismiss();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
