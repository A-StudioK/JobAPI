package com.example.searchjob.network

import com.example.searchjob.model.Job
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobService {
    @GET("api/v1/user")
    fun searchJob(@Query("description") description: String): Call<List<Job>>
}