package com.wuba.jiazheng.toolbox;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wuba.jiazheng.R;
import com.wuba.jiazheng.utils.MyHelp;

/**
 * Created by weizijie on 2018/8/16.
 */

public class NewCouponDialog extends PictureDialog {

    private String pageName;
    private String pageType;
    private String jumpEvent;
    private String closeEvent;
    private String closeEventBack;

    public NewCouponDialog(Builder builder) {
        super(builder.mContext,builder.mPath,builder.mWidth, builder.mHeight);
        setTargetUrl(builder.targetUrl);
        showClose(builder.showClose);
        this.pageName = "home_page_" + builder.name;
        this.pageType = "home_page_" + builder.name;
        this.jumpEvent = "home_page_jump_" + builder.name;
        this.closeEvent = "home_page_close_cancel_" + builder.name;
        this.closeEventBack = "home_page_close_button_" + builder.name;
    }

    @Override
    int getLayoutId() {
        return R.layout.new_coupon_dialog;
    }

    @Override
    void initContentView() {
    }

    @Override
    String getCloseEvent(boolean isBack) {
        if (isBack) {
            return closeEventBack;
        } else {
            return closeEvent;
        }
    }

    @Override
    String getPageName() {
        return pageName;
    }

    @Override
    String getJumpType() {
        return jumpEvent;
    }

    @Override
    String getPageType() {
        return pageType;
    }

    public static class Builder {
        private Context mContext;
        private String mPath;
        private int mWidth;
        private int mHeight;

        private String name;
        private String targetUrl;
        private boolean showClose;

        public Builder(@NonNull Context context, String path, int w, int h) {
            mContext = context;
            mPath = path;
            mWidth = w;
            mHeight = h;
        }

        public Builder setName(String val) {
            this.name = val;
            return this;
        }

        public Builder setTarget(String val) {
            this.targetUrl = val;
            return this;
        }

        public Builder setShowClose(boolean val) {
            this.showClose = val;
            return this;
        }

        public NewCouponDialog build() {
            return new NewCouponDialog(this);
        }
    }
}
