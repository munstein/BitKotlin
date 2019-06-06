package com.munstein.bitkotlin.main

import com.munstein.bitkotlin.services.BitcoinApiService
import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.Observable

/**
 * Created by @Munstein on 15/11/2017. --01:01
 */
class MainModel(private var service: BitcoinApiService) : MainMVP.IModel {

    override fun getBitcoinValues(): Observable<Map<String, BitcoinValues>> {
        return service.getBitcoinValues()
    }

}