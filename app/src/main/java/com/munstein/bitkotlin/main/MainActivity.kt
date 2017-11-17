package com.munstein.bitkotlin.main

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.munstein.bitkotlin.R
import com.munstein.bitkotlin.services.BitcoinApiService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMVP.IView, AdapterView.OnItemSelectedListener {

    private lateinit var presenter : MainPresenter
    private lateinit var currenciesAdapter : ArrayAdapter<String>

    private val dialog by lazy {
        ProgressDialog(this)
    }

    private val bitcoinApiService by lazy {
        BitcoinApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    private fun init(){
        main_spinner_currency.onItemSelectedListener = this
        dialog.isIndeterminate = true
        presenter = MainPresenter(this, MainModel(bitcoinApiService))
        presenter.getAndDisplayBitcoinValue()
    }

    override fun loadCurrencies(currencies : List<String>) {
        currenciesAdapter =  ArrayAdapter<String>(this,
                R.layout.custom_spinner_item, currencies)
        main_spinner_currency.adapter = currenciesAdapter
        currenciesAdapter.notifyDataSetChanged()
        presenter.onItemChange(main_spinner_currency.selectedItem.toString())
    }

    override fun loadValue(currency: String) {
        main_txt_value.text = currency
    }

    override fun showProgressDialog(msg: String) {
        dialog.setMessage(msg)
        dialog.show()
    }

    override fun hideProgressDialog() {
        dialog.dismiss()
    }

    override fun showErrorMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(adapter: AdapterView<*>?) {}

    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.onItemChange(main_spinner_currency.selectedItem.toString())
    }

}
