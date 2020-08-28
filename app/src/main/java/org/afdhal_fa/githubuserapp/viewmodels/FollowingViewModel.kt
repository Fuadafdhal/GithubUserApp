package org.afdhal_fa.githubuserapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.afdhal_fa.githubuserapp.models.Followers
import org.afdhal_fa.githubuserapp.webservices.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class FollowingViewModel : ViewModel() {
    private var dataFollowing = MutableLiveData<List<Followers>>()

    private var api = ApiClient.instance()

    fun fetchFollowing(token: String, userName: String) {
        api.following(token, userName).enqueue(object : Callback<List<Followers>> {
            override fun onFailure(call: Call<List<Followers>>, t: Throwable) {
                Timber.d(t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Followers>>,
                response: Response<List<Followers>>
            ) {
                if (response.isSuccessful) {
                    dataFollowing.postValue(response.body())
                } else {
                    Timber.d("Terjadi Kesalahan. Gagal mendapatkan respons")
                    Timber.d(response.message())
                    Timber.d(response.code().toString())
                }
            }

        })
    }

    fun getFollowing() = dataFollowing
}
