package com.pojo.sliderpojo

import com.google.gson.annotations.SerializedName

open class SliderItem (
    @SerializedName("id") val id : Long,
    @SerializedName("path") val path : String,
    @SerializedName("active") val active : Boolean,
    @SerializedName("position") val position : Int
) {
    override fun toString(): String {
        return "poster : $path\n" +
                "title: $active\n" +
                "overview: $id"
    }
}