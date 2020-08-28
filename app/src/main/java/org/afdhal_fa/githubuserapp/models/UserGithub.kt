package org.afdhal_fa.githubuserapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable

data class Detail(
    @SerializedName("login") var userName: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("avatar_url") var avatar_url: String? = null
)

data class Followers(
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
