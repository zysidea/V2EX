/**
 * Created by zys on 17-6-19.
 */

package com.zysidea.v2ex.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.zysidea.v2ex.R

class MainActivity : AppCompatActivity() {

    companion object

    var mCurrentTag: String = "HomeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

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
        var fragment: Fragment = HomeFragment.NewInstance()
        var tag: String = "HomeFragment"
        when (number) {
            1 -> {
                fragment = NodeFragment.NewInstance()
                tag="NodeFragment"
            }
        }

        val currentFragment = supportFragmentManager.findFragmentByTag(mCurrentTag)
        if (currentFragment.tag==tag){
            return
        }
        if (fragment.isAdded) {
            fragmentTransaction.hide(currentFragment).show(fragment)
        } else {
            fragmentTransaction.hide(currentFragment).add(R.id.content, fragment,tag)
        }
        fragmentTransaction.commit()
    }

}
