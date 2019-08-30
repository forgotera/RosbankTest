package ru.skillbranch.rosbanktest.model

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.rosbanktest.model.POJO.JsonAnswer


interface ListOfCurrencies {
    @GET("daily_json.js")
    fun getValute(): Observable<JsonAnswer>

    companion object Factory {
        private const val BASE_URL = "https://www.cbr-xml-daily.ru/"
        fun create(): ListOfCurrencies{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ListOfCurrencies::class.java)
        }
    }
}
