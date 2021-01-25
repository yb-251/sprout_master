package com.sprout.net

import android.util.Log
import com.sprout.net.repository.SystemRepository


/**
 * 数据仓库的代理类
 */
object Injection {
    // 创建数据仓库
    var repository: SystemRepository = SystemRepository()


    //创建一次
    val myRepository by lazy {
        Log.i("TAG", "init")
        SystemRepository()
    }

}