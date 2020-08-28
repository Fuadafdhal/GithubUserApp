package org.afdhal_fa.githubuserapp.webservices

import org.afdhal_fa.githubuserapp.models.Followers
import org.afdhal_fa.githubuserapp.models.Search
import org.afdhal_fa.githubuserapp.utils.ResponseDetail
import org.afdhal_fa.githubuserapp.utils.ResponseSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //Search
    @GET("search/users")
    fun searchUser(
        @Header("Authorization") token: String,
        @Query("q") userName: String
    ): Call<ResponseSearch<Search>>

    //Detail
    @GET("users/{username}")
    fun detailUser(
        @Header("Authorization") token: String,
        @Path("username") userName: String
    ): Call<ResponseDetail>

    @GET("/users/{username}")
    suspend fun findUserDetailByUsername(
        @Path("username") username: String,
        @Header("Authorization") token: String
    )

    //Followes
    @GET("users/{username}/followers")
    fun follower(
        @Header("Authorization") token: String,
        @Path("username") userName: String
    ): Call<MutableList<Followers>>

    //Followings
    @GET("users/{username}/following")
    fun following(
        @Header("Authorization") token: String,
        @Path("username") userName: String
    ): Call<List<Followers>>


}