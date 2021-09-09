package com.miskaa.assignment.borders.frontend.ui;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.jetbrains.annotations.NotNull;

/**
 * The current version of motionLayout (2.0.0-beta04) does not honor the position
 * of the RecyclerView, if it is wrapped in a SwipeRefreshLayout.
 * This is the case for the PullRequest screen: When scrolling back to top, the motionLayout transition
 * would be triggered immediately instead of only as soon as the RecyclerView scrolled back to top.
 *
 * This workaround checks if the SwipeRefresh layout can still scroll back up. If so, it does not trigger the motionLayout transition.
 */
public class SwipeRefreshMotionLayout extends MotionLayout {

        public void onNestedPreScroll(@NotNull View target, int dx, int dy, @NotNull int[] consumed, int type) {
                if (this.isInteractionEnabled()) {
                        if (!(target instanceof SwipeRefreshLayout)) {
                                super.onNestedPreScroll(target, dx, dy, consumed, type);
                        } else {
                                View recyclerView = ((SwipeRefreshLayout) target).getChildAt(0);
                                if (!(recyclerView instanceof RecyclerView)) {
                                        super.onNestedPreScroll(target, dx, dy, consumed, type);
                                } else {
                                        boolean canScrollVertically = recyclerView.canScrollVertically(-1);
                                        if (dy >= 0 || !canScrollVertically) {
                                                super.onNestedPreScroll(target, dx, dy, consumed, type);
                                        }
                                }
                        }
                }
        }

        public SwipeRefreshMotionLayout(@NotNull Context context) {
                super(context);
        }

        public SwipeRefreshMotionLayout(@NotNull Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
        }

        public SwipeRefreshMotionLayout(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
        }
}