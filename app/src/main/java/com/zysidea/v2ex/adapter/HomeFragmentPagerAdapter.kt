package com.zysidea.v2ex.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.zysidea.v2ex.R
import com.zysidea.v2ex.view.HomePageItemFragment

/**
 * Created by zys on 17-6-20.
 */
class HomeFragmentPagerAdapter(fm: FragmentManager, val context: Context?,private val currentPos: Int) : FragmentPagerAdapter(fm) {

    private val mNodes: Array<String>
    private var mCurrentFragment: Fragment? = null

    init {
        mNodes = context!!.resources.getStringArray(R.array.node)
    }



    override fun getItem(position: Int): Fragment {
        return Fragment.instantiate(context, HomePageItemFragment::class.java.name, getBundle())
    }

    override fun setPrimaryItem(container: ViewGroup?, position: Int, `object`: Any?) {
        mCurrentFragment = `object` as Fragment
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getCount(): Int {
        return mNodes!!.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mNodes[position]
    }

    fun getCurrentFragment(): Fragment? {
        return mCurrentFragment
    }

    private fun getBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("node",getPageTitle(currentPos) as String)
        return bundle
    }
}