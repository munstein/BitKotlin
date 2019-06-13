package com.munstein.bitkotlin

import com.munstein.bitkotlin.main.MainMVP
import com.munstein.bitkotlin.main.MainModel
import com.munstein.bitkotlin.main.MainPresenter
import com.munstein.bitkotlin.services.BitcoinApiService
import com.munstein.bitkotlin.services.BitcoinValues
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by @Munstein on 16/11/2017. --21:16
 */

class MainTest {

    @Mock
    private lateinit var view: MainMVP.IView

    @Mock
    private lateinit var mockModel: MainMVP.IModel

    private var presenter: MainPresenter? = null

    private var model = MainModel(BitcoinApiService.create())

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, model)
    }

    @Test
    fun assertWithMockValue() {
        val mockValue = HashMap<String, BitcoinValues>()
        val mockBitcoinValue = BitcoinValues()

        val symbol = "M$"
        val value = 540.90
        val key = "MCK"

        mockBitcoinValue._15m = value
        mockBitcoinValue.symbol = symbol
        mockValue.put(key, mockBitcoinValue)
        `when`(mockModel.getBitcoinValues())
                .thenReturn(Observable.just(mockValue))
        val result = mockModel.getBitcoinValues().blockingFirst()
        assertEquals(value, result.get(key)?._15m)
        assertEquals(symbol, result.get(key)?.symbol)
    }

    @Test
    fun assertWithRealValue() {
        val result = model.getBitcoinValues().blockingFirst()
        assertEquals(true, result.containsKey("USD"))
    }

}