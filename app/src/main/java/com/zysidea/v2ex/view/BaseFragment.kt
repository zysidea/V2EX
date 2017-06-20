package com.zysidea.v2ex.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zys on 17-6-20.
 */
abstract open class BaseFragment : Fragment() {

    private var mLayoutId:Int=0

    protected open fun setLayout(layoutId: Int) {
        this.mLayoutId =layoutId
    }
    protected abstract fun create()
    protected abstract fun createView(view:View)
    protected abstract fun activityCreated(savedInstanceState: Bundle?)

    protected abstract fun newInstance():BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView=inflater!!.inflate(mLayoutId, container, false)
        createView(rootView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated(savedInstanceState)
    }
}