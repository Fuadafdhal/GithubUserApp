package org.afdhal_fa.githubuserapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.afdhal_fa.githubuserapp.models.Search
import org.afdhal_fa.githubuserapp.utils.ResponseSearch
import org.afdhal_fa.githubuserapp.webservices.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private var dataSearch = MutableLiveData<List<Search>>()
    private var api = ApiClient.instance()

    fun fetchSearch(token: String, userName: String) {
        api.searchUser(token, userName).enqueue(object : Callback<ResponseSearch<Search>> {
            override fun onFailure(call: Call<ResponseSearch<Search>?>, t: Throwable) {
                Timber.d(t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseSearch<Search>?>,
                response: Response<ResponseSearch<Search>?>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() as ResponseSearch<Search>
                    val result = body.items
                    dataSearch.postValue(result)
                } else {
                    Timber.d("Terjadi Kesalahan. Gagal mendapatkan respons")
                }
            }
        })
    }

    fun getSearch() = dataSearch


}
