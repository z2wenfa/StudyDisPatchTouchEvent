package com.z2wenfa.toucheventdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListViewEx extends ListView {

    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        Log.d("test", "getFirstVisiblePosition():" + firstVisiblePosition + " getLastVisiblePosition()" + lastVisiblePosition);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getY();
                requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                boolean isMoveUp = mLastY - ev.getY() < 0;
                if (isMoveUp && firstVisiblePosition == 0) {
                    Log.d("tset", "needParent consume!");
                    requestDisallowInterceptTouchEvent(false);
//                    ((ViewGroup)getParent()).onTouchEvent(ev);
                    return false;
                } else if (!isMoveUp && lastVisiblePosition == getAdapter().getCount() - 1) {
                    Log.d("tset", "needParent consume!");
                    requestDisallowInterceptTouchEvent(false);
//                    ((ViewGroup)getParent()).onTouchEvent(ev);
                    return false;
                }
                break;
        }

        mLastY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    private float mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

}
