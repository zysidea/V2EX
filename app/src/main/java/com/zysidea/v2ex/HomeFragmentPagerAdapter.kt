package com.zysidea.v2ex

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by zys on 17-6-20.
 */
class HomeFragmentPagerAdapter(fm:FragmentManager,context:Context?,list: ArrayList<Fragment>):FragmentPagerAdapter(fm){

    private val mNodeTitles:Array<String>
    private var mCurrentFragment:Fragment?=null

    init {
        mNodeTitles=context?.resources!!.getStringArray(R.array.node)
    }

    override fun getItemPosition(`object`: Any?): Int {
        return super.getItemPosition(`object`)
    }

    override fun getItem(position: Int): Fragment {

    }

    override fun setPrimaryItem(container: ViewGroup?, position: Int, `object`: Any?) {
        mCurrentFragment=`object` as Fragment
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getCount(): Int {
       return mNodeTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mNodeTitles[position]
    }

    fun getCurrentFragment():Fragment?{
        return mCurrentFragment
    }
}