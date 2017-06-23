package com.zysidea.v2ex.network

/**
 * Created by zys on 17-6-22.
 */

object API {
    /**
     * 最热主题
     * Method: GET
     * Authentication: None
     */
    val API_HOT_TOPIC = "https://www.v2ex.com/api/topics/hot.json"
    /**
     * 最新主题 相当于全部
     * Method: GET
     * Authentication: None
     */
    val API_ALL_TOPIC = "https://www.v2ex.com/api/topics/latest.json"


    /**
     * 所有的节点
     * Method: GET
     * Authentication: None
     */
    val API_ALL_NODE = "https://www.v2ex.com/api/nodes/all.json"

    /**
     * 节点信息
     * Method: GET
     * Authentication: None
     * 接收参数：name：节点名称
     * https://www.v2ex.com/api/nodes/show.json?name=python
     */
    val API_NODE_INFO = "https://www.v2ex.com/api/nodes/show.json"

    /**
     * 指定节点下所有主题
     *  Method: GET
     * Authentication: None
     * 接收参数：
     *  id: 主题id 获取主题信息
     *  node_id：节点id
     *  node_name:节点名称
     *  username:用户名 获取该用户下所发表的主题
     * https://www.v2ex.com/api/topics/show.json?node_id=184
     */
    val API_ALL_TOPIC_OF_NODE = "https://www.v2ex.com/api/topics/show.json"


    /**
     * 获取主题回复
     *  Method: GET
     * Authentication: None
     * 接收参数：
     *  topic_id:主题id  可选
     *  page
     *  page_size
     */
    val API_TOPIC_INFO="https://www.v2ex.com/api/replies/show.json"

    /**
     * 用户信息
     * Method: GET
     * Authentication: None
     * 接收参数：username:用户名/id
     * https://www.v2ex.com/api/members/show.json?username=Livid
     * https://www.v2ex.com/api/members/show.json?id=1
     */
    val API_USER_PAGE = "https://www.v2ex.com/api/members/show.json"
}
