package com.zysidea.v2ex.view.home

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.zysidea.v2ex.R
import com.zysidea.v2ex.model.Topic
import com.zysidea.v2ex.network.API
import com.zysidea.v2ex.network.GsonRequest
import com.zysidea.v2ex.network.NetRequest
import com.zysidea.v2ex.util.VLogger
import com.zysidea.v2ex.view.BaseFragment
import com.zysidea.v2ex.view.home.adapter.HomePageItemAdapter

/**
 * Created by zys on 17-6-20.
 */
class HomePageItemFragment private constructor(private var listener:OnRecyclerViewScrollListener?): BaseFragment() {

    interface OnRecyclerViewScrollListener{
        fun onScrolled(dy:Int)
    }

    private var recyclerView: RecyclerView? = null
    private var srfl: SwipeRefreshLayout? = null
    private var node: String? = null
    private var adapter: HomePageItemAdapter? = null


    companion object {
        fun NewInstance(node: String,listener:OnRecyclerViewScrollListener?): HomePageItemFragment {
            val fragment = HomePageItemFragment(listener)
            val bundle = Bundle()
            bundle.putString("searchable", node)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun getLayout(): Int {
        return R.layout.fragment_home_page
    }

    override fun create() {
    }

    override fun createView(view: View) {
        node = arguments.getString("searchable")
        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        srfl = view.findViewById(R.id.srfl) as SwipeRefreshLayout
        recyclerView!!.setHasFixedSize(true)
        val llm = LinearLayoutManager(mContext)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = llm
        adapter = HomePageItemAdapter(mContext!!)
        recyclerView!!.addItemDecoration(DriverItemDecoration(LinearLayoutManager.VERTICAL,2))
        recyclerView!!.adapter = adapter
        setListener()
        getData()
    }

    override fun activityCreated(savedInstanceState: Bundle?) {

    }

    private fun setListener() {
        srfl!!.setOnRefreshListener({
            getData()
        })

        recyclerView!!.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (listener!=null){
                    listener!!.onScrolled(dy)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            }
        })
    }

    private fun getData() {
        srfl!!.isRefreshing = true
        var url: String
        when {
            node.equals("全部") -> url = API.API_ALL_TOPIC
            node.equals("最热") -> url = API.API_HOT_TOPIC
            else -> url = API.API_ALL_TOPIC_OF_NODE + "?node_name=" + node
        }

        val request: GsonRequest<Array<Topic>> = GsonRequest(Request.Method.GET, url, Array<Topic>::class.java,
                Response.Listener<Array<Topic>> {
                    response ->
                    srfl!!.isRefreshing = false
                    adapter!!.setData(response)
                },
                Response.ErrorListener {
                    error ->
                    srfl!!.isRefreshing = false
                    if(error.message!=null){
                        VLogger.LogInfo(error.message!!)
                    }

                }
        )
        NetRequest.getInstance(mContext!!).addToRequestQueue(request)
    }

}