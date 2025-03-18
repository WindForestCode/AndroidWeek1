package com.myschool.app1mvvm.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OffsetDecoration(
    @Px private val offset: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.top += offset
        outRect.left += offset
        outRect.right += offset
        val lastVisibleItemPosition =
            (parent.layoutManager as? GridLayoutManager)?.findLastVisibleItemPosition()
        val lastItemPosition = parent.adapter?.itemCount?.minus(1)
        if (lastVisibleItemPosition == lastItemPosition) {
            outRect.bottom += offset
        }
    }
}