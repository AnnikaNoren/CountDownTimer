package com.inspiredo.countdowntimer;

/**
 * Created by Annika on 10/3/15.
 */
public class Task {

    private String mTitle;
    private long mLength;

    public Task(String title, long length) {
        mTitle = title;
        mLength = length;
    }

    public long getLength() {
        return mLength;
    }

    public String getTitle() {
        return mTitle;
    }
}
