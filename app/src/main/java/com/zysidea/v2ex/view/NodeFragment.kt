package com.zysidea.v2ex.view

import android.os.Bundle
import android.view.View
import com.zysidea.v2ex.R

/**
 * Created by zys on 17-6-19.
 */
class NodeFragment private constructor() : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_node
    }

    override fun create() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createView(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {
        fun NewInstance(): NodeFragment {
            val fragment = NodeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}