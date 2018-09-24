package com.z2wenfa.toucheventdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

public class NestedScrollParentView extends LinearLayout implements NestedScrollingParent {

    public NestedScrollParentView(Context context) {
        super(context);
    }

    public NestedScrollParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mHeadViewHeight;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final View mHeadView = getChildAt(0);
        if (mHeadView != null) {
            mHeadView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mHeadViewHeight = mHeadView.getMeasuredHeight();
                    mHeadView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        boolean isUpAndCanMoveMore = dy > 0 && getScrollY() < mHeadViewHeight;
        boolean isDownAndCanMoveMore = dy < 0 && getScrollY() > 0;

        int willConsumedDy = dy;


        if (isUpAndCanMoveMore || isDownAndCanMoveMore) {
            if (getScrollY() + dy > mHeadViewHeight) {
                willConsumedDy = mHeadViewHeight - getScrollY();
            }
            if (getScrollY() + dy < 0) {
                willConsumedDy = 0 - getScrollY();
            }
            scrollBy(0, willConsumedDy);
            consumed[1] = willConsumedDy;
        }
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
