package com.z2wenfa.toucheventdemo.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

public class StickyLayout extends LinearLayout {

    private Scroller mScroller;

    private float mDonwX = 0;
    private float mDonwY = 0;

    private float mLastMoveX = 0;
    private float mLastMoveY = 0;

    private int touchSlop;

    public StickyLayout(Context context) {
        super(context);
        init(context);
    }

    public StickyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StickyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(getContext());
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case ACTION_DOWN:
                mDonwX = mLastMoveX = event.getX();
                mDonwY = mLastMoveY = event.getY();
                break;
            case ACTION_MOVE:
                int moveDistance = (int) (mLastMoveY - event.getY());
                scrollBy(0, moveDistance);
                mLastMoveX = event.getX();
                mLastMoveY = event.getY();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller != null && mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
