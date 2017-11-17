package com.munstein.bitkotlin

import com.munstein.bitkotlin.main.MainMVP
import com.munstein.bitkotlin.main.MainModel
import com.munstein.bitkotlin.main.MainPresenter
import com.munstein.bitkotlin.services.BitcoinApiService
import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.Observable
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by @Munstein on 16/11/2017. --21:16
 */

class MyTests {

    @Mock
    private lateinit var view : MainMVP.IView

    @Mock
    private lateinit var mockModel : MainMVP.IModel

    private var presenter : MainPresenter? = null

    private var model = MainModel(BitcoinApiService.create())

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, model)
    }

    @Test
    fun assertWithMockValue() {
        var mockValue = HashMap<String, BitcoinValues>()
        var mockBitcoinValue = BitcoinValues()

        var symbol = "M$"
        var value = 540.90
        var key = "MCK"

        mockBitcoinValue._15m = value
        mockBitcoinValue.symbol = symbol
        mockValue.put(key, mockBitcoinValue)
        `when`(mockModel.getBitcoinValues())
                .thenReturn(Observable.just(mockValue))
        var result = mockModel.getBitcoinValues().blockingFirst()
        Assert.assertEquals(540.90, result.get(key)!!._15m)
        Assert.assertEquals(540.90, result.get(key)!!.symbol)
    }

    @Test
    fun assertWithRealValue(){
        var result = model.getBitcoinValues().blockingFirst()
        Assert.assertEquals(true, result.containsKey("USD"))
    }

}