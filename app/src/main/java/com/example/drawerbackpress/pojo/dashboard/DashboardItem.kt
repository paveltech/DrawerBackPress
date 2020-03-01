package com.pojo.dashboard

import CategoryItems
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DashboardItem(@SerializedName("items") val items : ArrayList<CategoryItems>,
                         @SerializedName("totalPage") val totalPage : Int,
                         @SerializedName("page") val page : Int,
                         @SerializedName("count") val count : Int

):Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("items"),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalPage)
        parcel.writeInt(page)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DashboardItem> {
        override fun createFromParcel(parcel: Parcel): DashboardItem {
            return DashboardItem(parcel)
        }

        override fun newArray(size: Int): Array<DashboardItem?> {
            return arrayOfNulls(size)
        }
    }
}