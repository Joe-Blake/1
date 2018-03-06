package com.demo.joe.radiorv.footprints;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by weizijie on 2018/2/28.
 */

public class FootprintTransformer implements ViewPager.PageTransformer {

    private float alpha;
    private float scale;

    public FootprintTransformer(float alpha, float scale) {
        this.alpha = alpha;
        this.scale = scale;
    }

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setAlpha(alpha);
            page.setScaleX(scale);
            page.setScaleY(scale);
        } else if (position <= 1) {
            float scaleFactor = Math.max(scale, 1 - Math.abs(position));
            if (position < 0) {
                float lScale = 1 + 0.6f * position;
                page.setScaleX(lScale);
                page.setScaleY(lScale);
            } else {
                float rScale = 1 - 0.6f * position;
                page.setScaleX(rScale);
                page.setScaleY(rScale);
            }
            page.setAlpha(alpha + (scaleFactor - scale) / (1 - scale) * (1 - alpha));
        }
    }
}
