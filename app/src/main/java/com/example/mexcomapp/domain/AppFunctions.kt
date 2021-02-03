package com.example.mexcomapp.domain

import com.example.mexcomapp.data.MexItem

class AppFunctions {

    fun applyPromos(value: Double, dow: String, type: String): Double {
        if((dow == "MONDAY" || dow == "THURSDAY") && type.equals("Decorativo"))
            return value * 0.75
        else if((dow == "TUESDAY" || dow == "FRIDAY") && type.equals("Prenda"))
            return value * 0.75
        else if(dow == "WEDNESDAY" && type.equals("Util"))
            return value * 0.75
        else if(dow == "SATURDAY")
            return value * 0.9
        else
            return value
    }

    fun getTotal(alist: MutableList<MexItem>): Double {
        var value = 0.0
        for(i in 0 until alist.size)
            value += alist[i].lastPrice
        return value
    }
}