package com.demo.joe.radiorv.footprints;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.joe.radiorv.R;

import java.util.List;

/**
 * Created by weizijie on 2018/2/27.
 */

public class PagerItem extends RelativeLayout {

    public PagerItem(Context context, final int position, List<FootprintBean> beanList) {
        super(context);
        View root = LayoutInflater.from(context).inflate(R.layout.item_dropdownfootprint, this);
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zj", position + "");

            }
        });
        TextView textDes = (TextView) findViewById(R.id.item_des);
        textDes.setText(beanList.get(position).getName());

        TextView textPrice = (TextView) findViewById(R.id.item_price);
        textPrice.setText(beanList.get(position).getPrice());

        ImageView img = (ImageView) findViewById(R.id.item_img);

    }

}
