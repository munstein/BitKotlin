package com.munstein.bitkotlin.main

import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by @Munstein on 10/11/2017. --16:39
 */

class MainPresenter(private var view: MainMVP.IView, private var model: MainMVP.IModel) : MainMVP.IPresenter{

    private var disposable: Disposable? = null
    private lateinit var data : Map<String, BitcoinValues>

    override fun getAndDisplayBitcoinValue() {
        view.showProgress()
        disposable =  model.getBitcoinValues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result ->
                                view.hideProgress()
                                data = result
                                view.loadCurrencies(data.keys.toList())
                                view.hideError()
                        },
                        {   error ->
                                view.hideProgress()
                                view.showErrorMessage("Unable to fetch values. ")
                                view.showError()
                        }
                )
    }

    override fun onItemChange(value: String) {
        val formattedValue = StringBuilder()
        formattedValue.append(data.get(value)?.symbol)
        formattedValue.append(" ")
        formattedValue.append(data.get(value)?.value)
        view.loadValue(formattedValue.toString())
    }

    override fun onPause() {
        disposable?.dispose()
    }

}