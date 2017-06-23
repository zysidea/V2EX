package com.zysidea.v2ex.view.home

import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.zysidea.v2ex.R
import com.zysidea.v2ex.view.BaseFragment
import com.zysidea.v2ex.view.home.adapter.HomeFragmentPagerAdapter

/**
 * Created by zys on 17-6-19.
 */
class HomeFragment private constructor() : BaseFragment() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewpager: ViewPager? = null

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }


    companion object {
        fun NewInstance(): HomeFragment {
            val fragment = HomeFragment()
//            val bundle = Bundle()
//            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)
        mContext = activity
    }

    override fun create() {
        setHasOptionsMenu(true)
    }

    override fun createView(view: android.view.View) {
        toolbar = view.findViewById(com.zysidea.v2ex.R.id.toolbar) as Toolbar
        tabLayout = view.findViewById(com.zysidea.v2ex.R.id.tablayout) as TabLayout
        viewpager = view.findViewById(com.zysidea.v2ex.R.id.viewpager) as ViewPager
        init()
    }

    override fun activityCreated(savedInstanceState: android.os.Bundle?) {
    }

    private fun init() {
        toolbar!!.setTitle(com.zysidea.v2ex.R.string.app_name)
        toolbar!!.setTitleTextColor(Color.WHITE)
        (mContext as AppCompatActivity).setSupportActionBar(toolbar)
        viewpager!!.adapter = HomeFragmentPagerAdapter(childFragmentManager, mContext)
        viewpager!!.currentItem=0
        tabLayout!!.tabMode=TabLayout.MODE_SCROLLABLE
        tabLayout!!.setupWithViewPager(viewpager)
    }
}
