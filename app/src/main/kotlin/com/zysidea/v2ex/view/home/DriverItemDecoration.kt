package com.zysidea.v2ex.view.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zys on 17-6-26.
 */

class DriverItemDecoration(private val mOrientation: Int, private val mItemwidth: Int) : RecyclerView.ItemDecoration() {

    private val mPaint: Paint

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#E4E4E4")
        mPaint.style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        drawVertical(c, parent)
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mItemwidth)
        }
    }

    //纵向item的横向分隔线
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {

        val left = parent.paddingLeft
        val right = parent.measuredWidth - parent.paddingRight

        val itemSize = parent.childCount

        for (i in 0..itemSize - 1 - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin

            val bottom = top + mItemwidth
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }

    }

}
