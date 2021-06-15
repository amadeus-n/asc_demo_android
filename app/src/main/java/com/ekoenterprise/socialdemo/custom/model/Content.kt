package com.ekoenterprise.socialdemo.custom.model

import com.google.gson.annotations.SerializedName

data class Content(
    val feed_id: Int,
    val name: String,
    val description: String,
    val views: Int,
    @SerializedName("img_source") val imgSource: String
)
