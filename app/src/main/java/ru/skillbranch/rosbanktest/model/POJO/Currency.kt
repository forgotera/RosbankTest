package ru.skillbranch.rosbanktest.model.POJO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
 data class Currency (
     @SerializedName("CharCode")
     @Expose
     var charCode: String? = null,
     @SerializedName("Nominal")
    @Expose
    var nominal: Int? = null,
    @SerializedName("Name")
    @Expose
    var name: String? = null,
    @SerializedName("Value")
    @Expose
    var value: Double? = null
 )
