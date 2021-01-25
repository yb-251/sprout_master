package com.sprout.api

import com.sprout.model.LoginData
import com.sprout.net.BaseResp
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ServiceApi {


//    @POST("auth/refreshToken")  //刷新token
//    suspend fun refreshToken(): BaseResp<String>

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String, String>): BaseResp<LoginData>
}