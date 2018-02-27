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

import com.demo.joe.radiorv.R;
import com.demo.joe.radiorv.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weizijie on 2018/2/27.
 */

public class FootprintsDialog extends Dialog {

    private Context mContext;
    private ViewPager mViewPager;
    private TextView mIndex;
    private List<FootprintBean> mFootprintBeanList;


    public FootprintsDialog(@NonNull Context context, List<FootprintBean> footprintBeanList) {
        super(context, R.style.DropDown);
        mContext = context;
        mFootprintBeanList = footprintBeanList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.footprint_dialog, null);
        setContentView(rootView);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        Window dialogWindow = getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;
        lp.height = DisplayUtil.dip2px(mContext, 260);
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.TOP);
        dialogWindow.setWindowAnimations(R.style.DropDown);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        List<View> itemViews = new ArrayList<>();
        for (int i = 0; i < mFootprintBeanList.size(); i++) {
            PagerItem item = new PagerItem(mContext, i, mFootprintBeanList);
            itemViews.add(item);
        }
        DialogAdapter adapter = new DialogAdapter(itemViews);
        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
