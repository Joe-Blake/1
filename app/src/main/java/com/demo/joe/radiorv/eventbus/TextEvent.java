package com.demo.joe.radiorv.eventbus;

/**
 * Created by weizijie on 2018/8/11.
 */

public class TextEvent {
    private String mString;

    public TextEvent(String string) {
        mString = string;
    }

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }
}
