/**
 * Created by zys on 17-6-19.
 */

package com.zysidea.v2ex.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import com.zysidea.v2ex.R
import com.zysidea.v2ex.view.Node.NodeFragment
import com.zysidea.v2ex.view.home.BottomNavigationBehavior
import com.zysidea.v2ex.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object var currentTag: String = "HomeFragment"
    private var navigation:BottomNavigationView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation!!.selectedItemId=R.id.navigation_home

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_node -> {
                switchFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                switchFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(number: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment: BaseFragment = HomeFragment.NewInstance()
        var tag: String = "HomeFragment"
        when (number) {
            1 -> {
                fragment = NodeFragment.NewInstance()
                tag="NodeFragment"
            }
        }

        val currentFragment = supportFragmentManager.findFragmentByTag(currentTag)
        if(currentFragment!=null){
            if(currentFragment.tag.equals(tag)){
                return
            }
            fragmentTransaction.hide(currentFragment)
        }
        if (fragment.isAdded) {
            fragmentTransaction.hide(currentFragment).show(fragment)
        } else {
            fragmentTransaction.add(R.id.content, fragment,tag)
        }
        fragmentTransaction.commit()
    }

}
