package com.demo.joe.radiorv.aop.doubleclick;

/**
 * Created by weizijie on 2018/7/24.
 */

public class NoDoubleClickUtil {

    private static long lastClickTime = 0;
    private final static int SPACE_TIME = 500;

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick;
        if (currentTime - lastClickTime >
                SPACE_TIME) {
            isClick = false;
        } else {
            isClick = true;
        }
        lastClickTime = currentTime;
        return isClick;
    }
}
