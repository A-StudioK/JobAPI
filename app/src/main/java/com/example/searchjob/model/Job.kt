package com.example.searchjob.model

import com.google.gson.annotations.SerializedName

class Job (
    @SerializedName("job")
    val job: String,

    @SerializedName("empresa")
    val empresa: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String
)
