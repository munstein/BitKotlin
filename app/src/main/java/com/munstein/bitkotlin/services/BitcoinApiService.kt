package com.munstein.bitkotlin.services

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by @Munstein on 09/11/2017. --18:38
 */
interface BitcoinApiService {

    @GET("ticker/")
    fun getBitcoinValues() : Observable<Map<String, BitcoinValues>>

    companion object {
        fun create(): BitcoinApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://blockchain.info/pt/")
                    .build()

            return retrofit.create(BitcoinApiService::class.java)

        }
    }
}

