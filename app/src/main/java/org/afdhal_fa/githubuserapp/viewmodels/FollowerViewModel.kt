package org.afdhal_fa.githubuserapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.afdhal_fa.githubuserapp.models.Followers
import org.afdhal_fa.githubuserapp.webservices.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class FollowerViewModel : ViewModel() {
    private var dataFollower = MutableLiveData<List<Followers>>()
    private var api = ApiClient.instance()

    fun fetchFollower(token: String, userName: String) {
        api.follower(token, userName).enqueue(object : Callback<MutableList<Followers>> {
            override fun onFailure(call: Call<MutableList<Followers>>, t: Throwable) {
                Timber.d(t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<Followers>>,
                response: Response<MutableList<Followers>>
            ) {
                if (response.isSuccessful) {
                    dataFollower.postValue(response.body())
                } else {
                    Timber.d("Terjadi Kesalahan. Gagal mendapatkan respons")
                }
            }

        })
    }

    fun getFollower(): LiveData<List<Followers>> {
        return dataFollower
    }
}
