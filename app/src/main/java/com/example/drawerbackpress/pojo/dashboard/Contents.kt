import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Contents(

	@SerializedName("id") val id: Long?,
	@SerializedName("title") val title: String?,
	@SerializedName("type") val type: String?,
	@SerializedName("thumbnail") val imageUrl:String?,
	@SerializedName("status") val status: String?,
	@SerializedName("premium") val premium: Boolean,
	private var playState: Int = 0

):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Long::class.java.classLoader) as? Long,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readByte() != 0.toByte(),
		parcel.readInt()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(id)
		parcel.writeString(title)
		parcel.writeString(type)
		parcel.writeString(imageUrl)
		parcel.writeString(status)
		parcel.writeByte(if (premium) 1 else 0)
		parcel.writeInt(playState)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Contents> {
		override fun createFromParcel(parcel: Parcel): Contents {
			return Contents(parcel)
		}

		override fun newArray(size: Int): Array<Contents?> {
			return arrayOfNulls(size)
		}
	}
}