package com.demo.joe.radiorv.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by weizijie on 2018/6/12.
 */

public class SpannableUtils {

    /**
     * 指定分隔string分隔textView大小的Spannable
     *
     * @param size1 前半部分size
     * @param size2 后半部分size
     * @param separate
     * @param str
     * @return
     */
    public static Spannable pointSizeSpannable(int size1, int size2, String separate, String str) {
        int index = str.indexOf(separate);
        return sizeSpannable(size1, size2, index, str);
    }

    /**
     * 指定index分隔textView大小的Spannable
     * @param size1 前半部分size
     * @param size2 后半部分size
     * @param index 分割点，后半部分起始下标
     * @param str
     * @return
     */
    public static Spannable sizeSpannable(int size1, int size2, int index, String str) {
        Spannable sp = new SpannableStringBuilder(str);
        int end = str.length();
        if (index != -1) {
            sp.setSpan(new AbsoluteSizeSpan(size1, true), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            sp.setSpan(new AbsoluteSizeSpan(size2, true), index, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        } else {
            sp.setSpan(new AbsoluteSizeSpan(size1, true), 0, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return sp;
    }


    /**
     * 指定分隔string分隔textView颜色的Spannable
     *
     * @param color1 前色
     * @param color2 后色
     * @param separate 分割string
     * @param str
     * @return
     */
    public static Spannable pointColorSpannable(int color1, int color2, String separate, String str) {
        int index = str.indexOf(separate);
        return colorSpannable(color1, color2, index, str);
    }

    /**
     * 指定index分隔textView颜色的Spannable
     *
     * @param color1
     * @param color2
     * @param index
     * @param str
     * @return
     */
    public static Spannable colorSpannable(int color1, int color2, int index, String str) {
        Spannable sp = new SpannableStringBuilder(str);
        int end = str.length();
        if (index != -1) {
            sp.setSpan(new ForegroundColorSpan(color1), 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            sp.setSpan(new ForegroundColorSpan(color2), index, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        } else {
            sp.setSpan(new ForegroundColorSpan(color1), 0, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return sp;
    }



    /**
     * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE //前后都不包括
     *
     * Spannable.SPAN_INCLUSIVE_EXCLUSIVE  //前包括后不包括
     *
     * Spannable.SPAN_EXCLUSIVE_INCLUSIVE  //前不包括后包括
     *
     * Spannable.SPAN_INCLUSIVE_INCLUSIVE  //前后都包括
     *
     * 直接setSpan时不起作用,INCLUSIVE_INCLUSIVE，只有build.insert()方法时，Spannable标识才起作用，like：build.insert(1, "a");
     */
}
