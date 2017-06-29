package com.zysidea.v2ex.view.home.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.zysidea.v2ex.R
import com.zysidea.v2ex.view.home.HomePageItemFragment

/**
 * Created by zys on 17-6-20.
 */
class HomeFragmentPagerAdapter(fm: FragmentManager, context: Context?) : FragmentPagerAdapter(fm) {

    private val nodes: Array<String>

    init {
        nodes = context!!.resources.getStringArray(R.array.node)
    }

    override fun getItem(position: Int): Fragment {
        return HomePageItemFragment.NewInstance("全部")
    }

    override fun getCount(): Int {
        return nodes!!.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return nodes[position]
    }

}
