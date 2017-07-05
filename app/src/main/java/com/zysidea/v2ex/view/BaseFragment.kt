package com.zysidea.v2ex.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zys on 17-6-20.
 */
abstract class BaseFragment : Fragment() {

    protected var mContext:Context?=null

    protected abstract fun getLayout():Int
    protected abstract fun create()
    protected abstract fun createView(view:View)
    protected abstract fun activityCreated(savedInstanceState: Bundle?)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext=context
    }

    override fun onDetach() {
        super.onDetach()
        mContext=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        create()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView=inflater!!.inflate(getLayout(), container, false)
        createView(rootView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated(savedInstanceState)
    }
}