package com.demo.joe.radiorv.annotation;

import android.app.Activity;

/**
 * Created by weizijie on 2018/8/23.
 */

public class ViewBinder {
    private static final String SUFFIX = "$$ViewInjector";

    public static void bind(Activity activity) {
        bind(activity, activity);
    }

    /**
     * 1.寻找对应的代理类
     * 2.调用接口提供的绑定方法
     *
     * @param host
     * @param root
     */
    @SuppressWarnings("unchecked")
    private static void bind(final Object host, final Object root) {
        if (host == null || root == null) {
            return;
        }

        Class<?> aClass = host.getClass();
        String proxyClassFullName = aClass.getName() + SUFFIX;    //拼接代理类名

        try {
            Class<?> proxyClass = Class.forName(proxyClassFullName);
            ViewInjector viewInjector = (ViewInjector) proxyClass.newInstance();
            if (viewInjector != null) {
                viewInjector.inject(host, root);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public interface ViewInjector<T> {
        void inject(T t, Object source);
    }
}
