package com.munstein.bitkotlin.main

import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 10/11/2017. --16:39
 */

class MainPresenter : MainMVP.IPresenter{

    private var view : MainMVP.IView
    private var model : MainMVP.IModel
    private var disposable: Disposable? = null
    lateinit var data : Map<String, BitcoinValues>

    constructor(view : MainMVP.IView, model : MainMVP.IModel){
        this.view = view
        this.model = model
    }

    override fun getAndDisplayBitcoinValue() {
        view.showProgressDialog("loading...")
        disposable =  model.getBitcoinValues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result ->
                                view.hideProgressDialog()
                                data = result
                                view.loadCurrencies(data.keys.toList())
                                view.hideError()
                        },
                        {   error ->
                                view.hideProgressDialog()
                                view.showErrorMessage("Unable to fetch values. ")
                                view.showError()
                        }
                )
    }

    override fun onItemChange(value: String) {
        var formattedValue = StringBuilder()
        formattedValue.append(data.get(value)!!.symbol)
        formattedValue.append(" ")
        formattedValue.append(data.get(value)!!._15m)
        view.loadValue(formattedValue.toString())
    }

    override fun onPause() {
        disposable!!.dispose()
    }

}