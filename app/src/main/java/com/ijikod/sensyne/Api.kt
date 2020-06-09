package com.ijikod.sensyne

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Streaming
import retrofit2.http.Url

interface Api{

    @Streaming
    fun downloadFile(@Url fileUrl : String): Call<ResponseBody>

}