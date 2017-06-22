package com.zysidea.v2ex.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.android.volley.Request
import com.zysidea.v2ex.R
import com.zysidea.v2ex.adapter.HomeFragmentPagerAdapter
import com.zysidea.v2ex.model.Topic
import com.zysidea.v2ex.network.GsonRequest
import com.zysidea.v2ex.network.NetRequest

/**
 * Created by zys on 17-6-19.
 */
class HomeFragment private constructor() : BaseFragment() {

    private var mToolbar: Toolbar? = null
    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }


    companion object {
        fun NewInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = activity
    }

    override fun create() {
        setHasOptionsMenu(true)
    }

    override fun createView(view: View) {
        mToolbar = view.findViewById(R.id.toolbar)
        mTabLayout = view.findViewById(R.id.tablayout)
        mViewPager = view.findViewById(R.id.viewpager)
        mViewPager!!.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }
        })
        init()
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
    }

    private fun init() {
        mToolbar!!.setTitle(R.string.app_name)
        (mContext as AppCompatActivity).setSupportActionBar(mToolbar)
        mViewPager!!.adapter = HomeFragmentPagerAdapter(fragmentManager, mContext,mViewPager!!.currentItem)
        mTabLayout!!.setupWithViewPager(mViewPager)
    }
}
