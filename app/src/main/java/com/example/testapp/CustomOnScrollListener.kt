package com.example.testapp

import androidx.recyclerview.widget.RecyclerView

class CustomOnScrollListener(val onscrolled: (Int) -> Unit) : RecyclerView.OnScrollListener() {


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onscrolled(dy)
    }

    // private boolean loading = true;
    //    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    //    private UploadItemsListener uploadItemsListener;
    //
    //    public CustomOnScrollListener(UploadItemsListener uploadItemsListener) {
    //        this.uploadItemsListener = uploadItemsListener;
    //    }
    //
    //    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
    //        Log.d("onScrollStateChanged", "onScrollStateChanged");
    //    }
    //
    //    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
    //        super.onScrolled(recyclerView, dx, dy);
    //        if(dy > 0) //check for scroll down
    //        {
    //            visibleItemCount = recyclerView.getLayoutManager().getChildCount();
    //            totalItemCount = recyclerView.getLayoutManager().getItemCount();
    //            pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    //
    //            if (loading)
    //            {
    //                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount)
    //                {
    //                    loading = false;
    //                    uploadItemsListener.onLoadItems(totalItemCount);
    //                }
    //            }
    //        }
    //    }
    //
    //    public void setLoading(boolean loading) {
    //        this.loading = loading;
    //    }

}