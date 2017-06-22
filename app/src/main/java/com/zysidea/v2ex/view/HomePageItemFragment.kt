package com.zysidea.v2ex.view

import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.zysidea.v2ex.R
import com.zysidea.v2ex.model.Topic
import com.zysidea.v2ex.model.TopicList
import com.zysidea.v2ex.network.API
import com.zysidea.v2ex.network.GsonRequest
import com.zysidea.v2ex.network.NetRequest

/**
 * Created by zys on 17-6-20.
 */
class HomePageItemFragment :BaseFragment(){

    private var node:String?=null

    override fun getLayout(): Int {
        return R.layout.fragment_home_item
    }

    companion object {
        fun NewInstance(): HomePageItemFragment {
            val fragment = HomePageItemFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun create() {
    }

    override fun createView(view: View) {
        node=arguments.getString("node")
        getData()
    }

    override fun activityCreated(savedInstanceState: Bundle?) {

    }

    private fun getData(){
        var url:String
        when{
            node.equals("全部")-> url=API.API_ALL_TOPIC
            node.equals("最热")->url=API.API_HOT_TOPIC
            else->url=API.API_ALL_TOPIC_OF_NODE+"?node_name="+node
        }
        val request: GsonRequest<TopicList> = GsonRequest(Request.Method.GET,url,TopicList::class.java,null,
                Response.Listener<TopicList> {
                    response ->  {

                }

                },
                Response.ErrorListener {
                    error ->
                }
        )
        NetRequest.getInstance(mContext!!).addToRequestQueue(request)
    }

}