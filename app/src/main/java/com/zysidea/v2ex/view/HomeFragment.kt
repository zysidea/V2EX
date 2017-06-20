package com.zysidea.v2ex.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.zysidea.v2ex.R

/**
 * Created by zys on 17-6-19.
 */
class HomeFragment : BaseFragment() {

    private var mToolbar: Toolbar? = null
    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null
    private var mContext:Context?=null

    override fun setLayout(layoutId: Int) {
        super.setLayout(R.layout.fragment_home)
    }

    override fun newInstance(): BaseFragment {
        val fragment = HomeFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        return fragment
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext=activity
    }

    override fun create() {
        setHasOptionsMenu(true)
    }

    override fun createView(view: View) {
        mToolbar = view.findViewById(R.id.toolbar)
        mTabLayout=view.findViewById(R.id.tablayout)
        mViewPager=view.findViewById(R.id.viewpager)
        init()

    }

    override fun activityCreated(savedInstanceState: Bundle?) {
    }

    private fun init(){
        mToolbar!!.setTitle(R.string.app_name)
        (mContext as AppCompatActivity).setSupportActionBar(mToolbar)



        mViewPager.adapter=
        mTabLayout!!.setupWithViewPager(mViewPager)
    }
}
