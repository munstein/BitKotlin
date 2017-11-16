package com.munstein.bitkotlin

import com.munstein.bitkotlin.services.BitcoinApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val bitcoinApiService by lazy {
        BitcoinApiService.create()
    }
    var disposable: Disposable? = null

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun returns_something(){
        disposable = bitcoinApiService.getBitcoinValues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {}
                )
    }


}
