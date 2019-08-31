package ru.skillbranch.rosbanktest.extensions

import java.math.BigDecimal
import java.math.RoundingMode


fun Double.round(places:Int):Double{
    if (places < 0) throw IllegalArgumentException()

    var bd = BigDecimal(java.lang.Double.toString(this))
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()
}