package com.demo.joe.radiorv.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.joe.radiorv.R;

/**
 * Created by weizijie on 2017/12/29.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private String[] iconName = {"a", "b", "c", "d","a", "b", "c", "d","a", "b", "c"};

    public GridViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return iconName.length;
    }

    @Override
    public Object getItem(int position) {
        return iconName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (position < 4) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item2_girdview_layout,
                        parent, false);
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item1_girdview_layout,
                        parent, false);
            }
        }
        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText(iconName[position]);

        return convertView;
    }
}
