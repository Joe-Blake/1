package com.demo.joe.radiorv.aop.doubleclick;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by weizijie on 2018/7/24.
 */
@Aspect
public class NodoubleClick {

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void onClickLitener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.e("zj", "OnClick");
        if (!NoDoubleClickUtil.isDoubleClick()) {
            proceedingJoinPoint.proceed();
        }
    }
}
