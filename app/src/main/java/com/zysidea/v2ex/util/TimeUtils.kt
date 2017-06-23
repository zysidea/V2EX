package com.zysidea.v2ex.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zys on 17-6-22.
 */
object TimeUtils{

    /**
     * 刚刚
     * 几秒前
     * 1....59分钟
     * 1....23小时<几分钟>
     * 1....30天
     * 1....365天
     * 然后就是具体的yyyy-mm-dd
     */
    fun calculateTimeAgo(lastTouch:Long):String{
        val cal = Calendar.getInstance()
        val currentTime=cal.timeInMillis
        val timeDiff =currentTime-lastTouch
        val timeDiffs=timeDiff/1000
        when{
            timeDiffs<2->return "刚刚"
            timeDiffs<60->return "几秒前"
            timeDiffs<3600->return "${timeDiffs/60}分钟前"
            timeDiffs<3600*24->return "${timeDiffs/3600}小时前"
            timeDiffs<3600*24*30->return "${timeDiffs/(3600*24)}天前"
            timeDiffs<3600*24*30*12->return "${timeDiffs/(3600*24*30)}天前"
            else ->{
                val sdf=SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                return sdf.format(lastTouch)
            }
        }
    }
}