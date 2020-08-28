package org.afdhal_fa.githubuserapp.utils

import com.google.gson.annotations.SerializedName


data class ResponseDetail(
    @SerializedName("login") var login: String? = null,
    @SerializedName("avatar_url") var avatar_url: String? = null,
    @SerializedName("name") var name: String? = null
)

data class ResponseSearch<T>(
    @SerializedName("total_count") var total_count: Int? = null,
    @SerializedName("incomplete_results") var incomplete_results: Boolean? = null,
    @SerializedName("items") var items: List<T>? = null
)
