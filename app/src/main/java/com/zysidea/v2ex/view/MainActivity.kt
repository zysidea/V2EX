/**
 * Created by zys on 17-6-19.
 */

package com.zysidea.v2ex.view

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.zysidea.v2ex.R
import com.zysidea.v2ex.view.node.NodeFragment
import com.zysidea.v2ex.view.home.HomeFragment
import com.zysidea.v2ex.view.home.HomePageItemFragment
import com.zysidea.v2ex.view.mine.MineFragment


class MainActivity : AppCompatActivity(), HomePageItemFragment.OnRecyclerViewScrollListener {


    private var currentFragment: BaseFragment?=null
    private var navigation: BottomNavigationView? = null
    private var isHidden: Boolean = false
    private var translationAnimator: ViewPropertyAnimatorCompat? = null
    private var translationObjectAnimator:ObjectAnimator?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation!!.selectedItemId = R.id.navigation_home
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
        var fragment: BaseFragment?=null
        when (number) {
            0->{
                fragment=HomeFragment.NewInstance(this)
            }
            1 -> {
                fragment = NodeFragment.NewInstance()
            }
            2->{
                fragment=MineFragment()
            }
        }
        if (currentFragment != null&&currentFragment!=fragment) {
            fragmentTransaction.hide(currentFragment)
        }
        if (fragment!!.isAdded) {
            fragmentTransaction.show(fragment)
        } else {
            fragmentTransaction.add(R.id.content, fragment)
        }
        fragmentTransaction.commit()
        currentFragment=fragment
    }

    override fun onScrolled(dy: Int) {
        if (dy < 0) {
            handleDirection(true)
        } else if (dy > 0) {
            handleDirection(false)
        }
    }

    private fun handleDirection(isScrollDown: Boolean) {

        if (isScrollDown && isHidden) {
            isHidden = false
            animateOffset(0, true)
        } else if (!isScrollDown && !isHidden) {
            isHidden = true
            animateOffset(navigation!!.height, true)
        }
    }

    private fun animateOffset(offset: Int, withAnimation: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            ensureOrCancelObjectAnimation( offset, withAnimation)
            translationObjectAnimator!!.start()
        } else {
            ensureOrCancelAnimator(withAnimation)
            translationAnimator!!.translationY(offset.toFloat()).start()
        }
    }

    private fun ensureOrCancelAnimator(withAnimation: Boolean) {
        if (translationAnimator == null) {
            translationAnimator = ViewCompat.animate(navigation!!)
            translationAnimator!!.setDuration(if (withAnimation) 300 else 0)
            translationAnimator!!.interpolator=LinearOutSlowInInterpolator()
        } else {
            translationAnimator!!.setDuration(if (withAnimation) 300 else 0)
            translationAnimator!!.cancel()
        }
    }

    /**
     * 低于4.4
     */
    private fun ensureOrCancelObjectAnimation(offset: Int, withAnimation: Boolean) {

        if (translationObjectAnimator != null) {
            translationObjectAnimator!!.cancel()
        }
        translationObjectAnimator = ObjectAnimator.ofFloat(navigation,View.TRANSLATION_Y, offset.toFloat())
        translationObjectAnimator!!.setDuration(if (withAnimation) 300 else 0)
        translationObjectAnimator!!.interpolator=LinearOutSlowInInterpolator()
    }

}

