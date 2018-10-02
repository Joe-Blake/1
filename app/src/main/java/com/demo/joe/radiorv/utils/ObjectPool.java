package com.demo.joe.radiorv.utils;

import android.util.SparseArray;

/**
 * Created by weizijie on 2018/10/2.
 */

public abstract class ObjectPool<T> {
    private SparseArray<T> freePool;
    private SparseArray<T> lentPool;
    private int maxCapacity;

    private void initialize(final int initCapacity) {
        freePool = new SparseArray<>();
        lentPool = new SparseArray<>();
        for (int i = 0; i < initCapacity; i++) {
            freePool.put(i,create());
        }
    }

    protected abstract T create();

    public ObjectPool(int initCapacity, int maxCapacity) {
        initialize(initCapacity);
        this.maxCapacity = maxCapacity;
    }

    public ObjectPool(int maxCapacity) {
        this(maxCapacity / 2, maxCapacity);
    }

    public T acquire() {
        T t = null;
        synchronized (freePool) {
            int freeSize = freePool.size();
            for (int i = 0; i < freeSize; i++) {
                int key = freePool.keyAt(i);
                t = freePool.get(key);
                if (t != null) {
                    this.lentPool.put(key, t);
                    this.freePool.remove(key);
                    return t;
                }
            }
            if (t == null && lentPool.size() + freePool.size() < maxCapacity) {
                t = create();
                lentPool.put(lentPool.size() + freeSize, t);
            }
        }
        return t;
    }

    public void release(T t) {
        if (t == null) {
            return;
        }
        int key = lentPool.indexOfValue(t);
        restore(t);
        this.freePool.put(key, t);
        this.lentPool.remove(key);
    }

    protected void restore(T t) {

    }
}
