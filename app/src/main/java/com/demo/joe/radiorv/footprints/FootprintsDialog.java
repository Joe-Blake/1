package com.demo.joe.radiorv.footprints;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizijie on 2018/2/27.
 */

public class FootprintsDialog extends Dialog {

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
                Toast.makeText(mContext, "more....", Toast.LENGTH_SHORT).show();
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
}
