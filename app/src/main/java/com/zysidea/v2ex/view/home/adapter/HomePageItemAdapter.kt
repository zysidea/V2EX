package com.zysidea.v2ex.view.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.zysidea.v2ex.R
import com.zysidea.v2ex.model.Topic
import com.zysidea.v2ex.util.TimeUtils
import com.zysidea.v2ex.util.VLogger

/**
 * Created by zys on 17-6-22.
 */

class HomePageItemAdapter(private val context: Context) : RecyclerView.Adapter<HomePageItemAdapter.ViewHolder>() {

    private var dataArray: Array<Topic>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_home_page_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (dataArray == null) {
            return
        }
        val topic = dataArray!![position]
        val member = topic.member
        val node = topic.node
        //头像
        val imageUrl = "http:"+member.avatarNormal
        Glide.with(context).load(imageUrl).into(holder.avatar)
        //title
        holder.title.text = topic.title
        //searchable
        holder.node.text = node.title
        //username
        holder.userName.text = member.userName
        //timeago
        holder.timeAgo.text = TimeUtils.calculateTimeAgo(topic.lastTouched)
        //replies
        holder.replies.text=topic.replies.toString()

    }

    override fun getItemId(position: Int): Long {
        if (dataArray==null){
            return RecyclerView.NO_ID
        }
        val topic = dataArray!![position]
        return topic.id.toLong()
    }

    override fun getItemCount(): Int {
        if (dataArray == null) {
            return 0
        }
        return dataArray!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView
        val node: TextView
        val userName: TextView
        val timeAgo: TextView
        val title: TextView
        val replies:TextView


        init {
            this.avatar =itemView.findViewById(R.id.avatar) as ImageView
            this.title = itemView.findViewById(R.id.title) as TextView
            this.node = itemView.findViewById(R.id.node)as TextView
            this.userName = itemView.findViewById(R.id.username)as TextView
            this.timeAgo = itemView.findViewById(R.id.timeago)as TextView
            replies=itemView.findViewById(R.id.replies) as TextView
        }
    }

    fun setData(data: Array<Topic>?) {
        if (data == null) {
            return
        }
        dataArray = data
        notifyDataSetChanged()
    }
}
