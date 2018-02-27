package com.demo.joe.radiorv.footprints;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.joe.radiorv.R;

import java.util.List;

/**
 * Created by weizijie on 2018/2/27.
 */

public class PagerItem extends RelativeLayout {
    public PagerItem(Context context, int position, List<FootprintBean> beanList) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_dropdownfootprint, this);

        TextView textDes = (TextView) findViewById(R.id.item_des);
        textDes.setText(beanList.get(position).getName());

        TextView textPrice = (TextView) findViewById(R.id.item_price);
        textPrice.setText(beanList.get(position).getPrice());

        ImageView img = (ImageView) findViewById(R.id.item_img);
    }
}
