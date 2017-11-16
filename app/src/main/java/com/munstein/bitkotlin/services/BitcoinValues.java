/**
 * Created by @Munstein on 09/11/2017. --21:45
 */


package com.munstein.bitkotlin.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BitcoinValues {

    @SerializedName("15m")
    @Expose
    private Double _15m;
    @SerializedName("last")
    @Expose
    private Double last;
    @SerializedName("buy")
    @Expose
    private Double buy;
    @SerializedName("sell")
    @Expose
    private Double sell;
    @SerializedName("symbol")
    @Expose
    private String symbol;

    public Double get15m() {
        return _15m;
    }

    public void set15m(Double _15m) {
        this._15m = _15m;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}