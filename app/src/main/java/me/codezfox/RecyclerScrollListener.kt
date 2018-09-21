package me.codezfox

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class RecyclerScrollListener(

        private val onLoadNewPage: () -> Unit,
        private val countScreenBuffer: Int = 1

) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)


        val layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount

        if (((lastVisibleItemPosition + visibleItemCount * countScreenBuffer) >= totalItemCount && firstVisibleItemPosition >= 0)) {
            onLoadNewPage.invoke()
        }

    }

}