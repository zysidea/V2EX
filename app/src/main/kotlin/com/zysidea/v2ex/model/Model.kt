package com.zysidea.v2ex.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zys on 17-6-22.
 */


data class Topic(val id: Int,
                 val title: String,
                 val url: String,
                 val content: String,
                 @SerializedName("content_rendered") val contentRendered: String,
                 val replies: Int,
                 val member: Member,
                 val node: Node,
                 val created: Long,
                 @SerializedName("last_modified") val lastModified: Long,
                 @SerializedName("last_touched") val lastTouched: Long
)

data class Member(val id: Int,
                  @SerializedName("username") val userName: String,
                  val tagline: String,
                  @SerializedName("avatar_mini") val avatarMini: String,
                  @SerializedName("avatar_normal") val avatarNormal: String,
                  @SerializedName("avatar_large") val avatarLarge: String)


data class Node(val id: Int,
                val name: String,
                val title: String,
                @SerializedName("title_alternative") val titleAlternative: String,
                val url: String,
                val topics: Int,
                @SerializedName("avatar_mini") val avatarMini: String,
                @SerializedName("avatar_normal") val avatarNormal: String,
                @SerializedName("avatar_large") val avatarLarge: String)

