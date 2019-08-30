package ru.skillbranch.rosbanktest.model.POJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JsonAnswer(
    @SerializedName("Valute")
    @Expose
    var valute: Map<String,Currency> = mapOf())

