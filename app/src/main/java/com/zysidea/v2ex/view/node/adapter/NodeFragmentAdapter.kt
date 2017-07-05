package com.zysidea.v2ex.view.node.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zysidea.v2ex.R
import com.zysidea.v2ex.model.Node

/**
 * Created by zys on 17-7-4.
 */
class NodeFragmentAdapter(private val context: Context) : RecyclerView.Adapter<NodeFragmentAdapter.ViewHolder>() {


    private var dataArray: Array<Node>? = null


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_node_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (dataArray == null) {
            return
        }
        val node = dataArray!![position]
        holder!!.title.text=node.title
        holder!!.topics.text=node.topics.toString()+"个话题"
    }

    override fun getItemId(position: Int): Long {
        if (dataArray==null){
            return RecyclerView.NO_ID
        }
        val node = dataArray!![position]
        return node.id.toLong()
    }

    override fun getItemCount(): Int {
        if (dataArray == null) {
            return 0
        }
        return dataArray!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val topics: TextView

        init {
            title = itemView.findViewById(R.id.title) as TextView
            topics = itemView.findViewById(R.id.topics) as TextView
        }
    }

    fun setData(data: Array<Node>?) {
        if (data == null) {
            return
        }
        dataArray = data
        notifyDataSetChanged()
    }
}