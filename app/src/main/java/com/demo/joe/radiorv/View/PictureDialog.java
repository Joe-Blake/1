package com.wuba.jiazheng.toolbox;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.daojia.baseutils.DisplayUtil;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wuba.jiazheng.R;
import com.wuba.jiazheng.log.DJLogCollector;
import com.wuba.jiazheng.utils.MyHelp;
import com.wuba.jiazheng.views.ScaleLayout;

import java.math.BigDecimal;

/**
 * Created by weizijie on 2018/8/15.
 */

public abstract class PictureDialog extends Dialog{

    protected Context mContext;
    private View mContentView;
    private LinearLayout dialog_content_ll;
    private SimpleDraweeView mBackground;
    private ImageView close;
    private boolean isBack = false;//是否点击关闭/返回

    abstract int getLayoutId();

    abstract void initContentView();

    abstract String getCloseEvent(boolean isBack);

    abstract String getPageName();

    abstract String getJumpType();

    abstract String getPageType();

    public PictureDialog(@NonNull Context context, String path, int w, int h) {
        super(context, R.style.MyDialog);
        mContext = context;
        init();
        initBackground(path, w, h);
    }

    @Override
    public void show() {
        if (!TextUtils.isEmpty(getPageName()) && !TextUtils.isEmpty(getPageType())) {
            DJLogCollector.writeNativeLoadLog(getPageName(), getPageType());
        }
        super.show();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContentView = LayoutInflater.from(mContext).inflate(
                R.layout.layout_pic_dialog, null);
        setContentView(mContentView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = DisplayUtil.getScreenWidth(mContext);
        getWindow().setAttributes(lp);
        dialog_content_ll = easyFindViewById(R.id.dialog_content_ll);
        View content = this.getLayoutInflater().inflate(getLayoutId(), null);
        dialog_content_ll.addView(content, 0,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        initContentView();
        mBackground = mContentView.findViewById(R.id.gif);
        close = mContentView.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setCanceledOnTouchOutside(true);
    }

    private void initBackground(String path, int w, int h) {
        float width = w;
        float height = h;
        mBackground.setAspectRatio(BigDecimal.valueOf(width).divide(BigDecimal.valueOf(height),4,BigDecimal.ROUND_HALF_UP).floatValue());
        ViewGroup.LayoutParams bgParams = mBackground.getLayoutParams();
        bgParams.width = DisplayUtil.dip2px(mContext, 290);
        bgParams.height = BigDecimal.valueOf(height)
                .divide(BigDecimal.valueOf(width),4,BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(DisplayUtil.dip2px(mContext, 290))).intValue();
        mBackground.setLayoutParams(bgParams);
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_FILE_SCHEME)
                .path(path)
                .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        mBackground.setController(draweeController);
    }

    public void showClose(boolean isShow) {
        if (isShow) {
            close.setVisibility(View.VISIBLE);
        } else {
            close.setVisibility(View.GONE);
        }
    }

    public void setTargetUrl(String url) {
        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(getJumpType())) {
                    writeLog(getJumpType());
                }
                dismiss();
                MyHelp.showImgWeb(mContext, url);
            }
        });
    }

    private void writeLog(String eventId) {
        if (!TextUtils.isEmpty(getPageName()) && !TextUtils.isEmpty(getPageType())) {
            DJLogCollector.writeClickLog(eventId, getPageName(), getPageType());
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (!TextUtils.isEmpty(getCloseEvent(false)) && !isBack) {
            writeLog(getCloseEvent(false));
        }
    }

    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(getCloseEvent(true))) {
            writeLog(getCloseEvent(true));
            isBack = true;
        }
        super.onBackPressed();
    }

    public <V extends View> V easyFindViewById(int id) {
        return (V) super.findViewById(id);
    }

}
