package ru.skillbranch.rosbanktest.model.POJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JsonAnswer {

    @SerializedName("Date")
    @Expose
    var date: String? = null
    @SerializedName("PreviousDate")
    @Expose
    var previousDate: String? = null
    @SerializedName("PreviousURL")
    @Expose
    var previousURL: String? = null
    @SerializedName("Timestamp")
    @Expose
    var timestamp: String? = null
    @SerializedName("Valute")
    @Expose
    var valute: Map<String,Currency> = mapOf()

}
