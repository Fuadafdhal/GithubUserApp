package org.afdhal_fa.githubuserapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.afdhal_fa.githubuserapp.models.Detail
import org.afdhal_fa.githubuserapp.utils.ResponseDetail
import org.afdhal_fa.githubuserapp.webservices.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private var dataDetail = MutableLiveData<Detail>()
    private var api = ApiClient.instance()

    fun fetchDetail(token: String, userName: String) {
        api.detailUser(token, userName).enqueue(object : Callback<ResponseDetail?> {
            override fun onFailure(call: Call<ResponseDetail?>, t: Throwable) {
                println(t.message.toString())
            }

            override fun onResponse(
                call: Call<ResponseDetail?>,
                response: Response<ResponseDetail?>
            ) {
                if (response.isSuccessful) {

                    print("Getdata Responses")
                    val body = response.body() as ResponseDetail
                    val result = Detail(body.login, body.name, body.avatar_url)
                    dataDetail.postValue(result)
                } else {
                    println("Terjadi Kesalahan. Gagal mendapatkan respons")
                }
            }
        })

    }

    fun getDetail(): LiveData<Detail> {
        return dataDetail
    }
}

