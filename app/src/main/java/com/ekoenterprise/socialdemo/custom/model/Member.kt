package com.ekoenterprise.socialdemo.custom.model

import com.google.gson.annotations.SerializedName

data class Member(val id: Int,
                  val slug: String,
                  val name: String,
                  val generation: Int,
                  val instagram: String,
                  val data:ArrayList<Content>,
                  @SerializedName("avatar_image") val avatarImage: String,
                  @SerializedName("profile_image") val profileImage: String,
                  @SerializedName("avatar_image_2x") val avatarImage2x: String,
                  @SerializedName("profile_image_2x") val profileImage2x: String )