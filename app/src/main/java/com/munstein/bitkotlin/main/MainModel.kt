package com.munstein.bitkotlin.main

import com.munstein.bitkotlin.services.BitcoinApiService
import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.Observable

/**
 * Created by @Munstein on 15/11/2017. --01:01
 */
class MainModel : MainMVP.IModel {

    private lateinit var coreData : Map<String, BitcoinValues>
    private var service : BitcoinApiService

    constructor(service : BitcoinApiService){
        this.service = service
    }

    override fun getBitcoinValues(): Observable<Map<String, BitcoinValues>> {
        return service.getBitcoinValues()
    }

    /*override fun getCurrencies(): Collection<String> {
        return coreData.keys
    }

    override fun getValues(currency: String): BitcoinValues {
        return coreData.get(currency)!!
    }*/
}