package com.zysidea.v2ex.view.node

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.zysidea.v2ex.R
import com.zysidea.v2ex.model.Node
import com.zysidea.v2ex.network.API
import com.zysidea.v2ex.network.GsonRequest
import com.zysidea.v2ex.network.NetRequest
import com.zysidea.v2ex.util.VLogger
import com.zysidea.v2ex.view.BaseFragment
import com.zysidea.v2ex.view.node.adapter.NodeFragmentAdapter

/**
 * Created by zys on 17-6-19.
 */
class NodeFragment private constructor() : BaseFragment() {


    private var adapter: NodeFragmentAdapter? = null

    private var toolbar: Toolbar? = null
    private var srfl: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null

    companion object {
        fun NewInstance(): NodeFragment {
            val fragment = NodeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_node
    }

    override fun create() {
        setHasOptionsMenu(true)
    }

    override fun createView(view: View) {
        toolbar = view.findViewById(R.id.toolbar) as Toolbar
        toolbar!!.setTitle(com.zysidea.v2ex.R.string.title_node)
        toolbar!!.setTitleTextColor(Color.WHITE)
        (mContext as AppCompatActivity).setSupportActionBar(toolbar)
        srfl = view.findViewById(R.id.srfl) as SwipeRefreshLayout
        recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        val glm = GridLayoutManager(mContext, 2)
        recyclerView!!.layoutManager = glm
        adapter = NodeFragmentAdapter(mContext!!)
        recyclerView!!.adapter = adapter
        setListener()
        getData()
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
    }



    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_node, menu)
        val searchItem = menu!!.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView

        //关闭搜索的按钮
        val searchCloseIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
        searchCloseIcon.setImageResource(R.drawable.ic_close_white_24dp)
        //toolbar上面的搜索安妮
        val searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_button) as ImageView
        searchIcon.setImageResource(R.drawable.ic_search_white_24dp)
        //搜索字体
        val searchText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText
        searchText.setTextColor(Color.WHITE)
        searchText.setHint(R.string.search_hint)

                searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
        })
    }


    private fun setListener() {
        srfl!!.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        srfl!!.isRefreshing = true
        val request = GsonRequest(Request.Method.GET, API.API_ALL_NODE, Array<Node>::class.java,
                Response.Listener<Array<Node>> {
                    response ->
                    srfl!!.isRefreshing = false
                    adapter!!.setData(response)
                },
                Response.ErrorListener {
                    error ->
                    srfl!!.isRefreshing = false
                    if (error.message != null) {
                        VLogger.LogInfo(error.message!!)
                    }
                })
        NetRequest.getInstance(mContext!!).addToRequestQueue(request)
    }

}