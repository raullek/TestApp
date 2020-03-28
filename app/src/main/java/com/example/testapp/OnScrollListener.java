package com.example.testapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OnScrollListener extends RecyclerView.OnScrollListener {
    private int currentPage = 1;
    private int totalItemCount;
    private int visibleItemCount;
    private int firstVisibleItem;

    private int lastVisibleItem = 0;
    private boolean checkScroll = true;

    private boolean loading = false;
    private int currentTotalItems = 0;

    private LinearLayoutManager mLinearLayoutManager;
    private ConstraintLayout mConstraintLay;

    public OnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public OnScrollListener(LinearLayoutManager linearLayoutManager, ConstraintLayout mConstraintLay) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.mConstraintLay = mConstraintLay;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (totalItemCount < currentTotalItems) {
            this.currentPage = 1;
            this.currentTotalItems = totalItemCount;

            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > currentTotalItems)) {
            loading = false;
            currentTotalItems = totalItemCount;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + 5)) {
            onLoadMore(currentPage++);
            loading = true;
        }

        if (lastVisibleItem != firstVisibleItem) {
            if (lastVisibleItem < firstVisibleItem) {
                if (checkScroll) {
                    checkScroll = false;
                    showHideFloatButton(true);
                    onScroll(recyclerView, dx, dy, true);
                }
            } else {
                if (!checkScroll) {
                    checkScroll = true;
                    showHideFloatButton(false);
                    onScroll(recyclerView, dx, dy, false);
                }
            }
        }

        lastVisibleItem = firstVisibleItem;
    }

    public void resetEndlessRecyclerView() {
        this.currentTotalItems = 0;
        this.loading = false;
    }

    public void onScroll(RecyclerView recyclerView, int dx, int dy, boolean onScroll) {
    }

    public void onLoadMore(int currentPage) {
    }

    //Hide or show the FloatingActionButton based on the list scroll
    private void showHideFloatButton(boolean status) {
        if (status) {
            mConstraintLay.animate()
                    .translationY(0)
                    .setDuration(400)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mConstraintLay.setVisibility(View.GONE);
                        }
                    });
//            mConstraintLay.setAlpha(1f);
//            mConstraintLay.setTranslationY(0f);
//            mConstraintLay.animate().alpha(0f)
//                    .translationY(mConstraintLay.getHeight())
//                    .setDuration(175L)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            mConstraintLay.setVisibility(FrameLayout.GONE);
//                        }
//                    }).start();
        } else {
            mConstraintLay.animate()
                    .translationY(1)
                    .setDuration(400)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mConstraintLay.setVisibility(View.VISIBLE);
                        }
                    });
//            mConstraintLay.setVisibility(View.VISIBLE);
//            mConstraintLay.setAlpha(0f);
//            mConstraintLay.setTranslationY(mConstraintLay.getHeight());
//            mConstraintLay.animate()
//                    .alpha(1f)
//                    .translationY(0f)
//                    .setDuration(175L)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            mConstraintLay.setVisibility(FrameLayout.VISIBLE);
//                        }
//                    })
//                    .start();
        }
    }
}
