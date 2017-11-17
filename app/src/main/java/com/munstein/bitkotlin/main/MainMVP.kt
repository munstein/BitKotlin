package com.munstein.bitkotlin.main

import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.Observable

/**
 * Created by @Munstein on 09/11/2017. --18:07
 */

interface MainMVP {

    interface IModel{
        fun getBitcoinValues() : Observable<Map<String, BitcoinValues>>
    }

    interface IView {
        fun loadCurrencies(currencies : List<String>)
        fun loadValue(currency : String)
        fun showProgressDialog(msg : String)
        fun hideProgressDialog()
        fun showErrorMessage(msg : String)
    }

    interface IPresenter{
        fun getAndDisplayBitcoinValue()
        fun onItemChange(value : String)
        fun onPause()
    }
}