/**
 * Created by @Munstein on 09/11/2017. --21:45
 */


package com.munstein.bitkotlin.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BitcoinValues(
        @SerializedName("15m")
        @Expose
        var value: Double? = null,
        @SerializedName("last")
        @Expose
        var last: Double? = null,
        @SerializedName("buy")
        @Expose
        var buy: Double? = null,
        @SerializedName("sell")
        @Expose
        var sell: Double? = null,
        @SerializedName("symbol")
        @Expose
        var symbol: String? = null
)