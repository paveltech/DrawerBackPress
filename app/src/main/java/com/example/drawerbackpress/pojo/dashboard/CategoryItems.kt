import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CategoryItems(

	@SerializedName("id") val id: Long?,
	@SerializedName("title") val title: String?,
	@SerializedName("position") val position: Int,
	@SerializedName("type") val type:String?,
	@SerializedName("contents") val contents: ArrayList<Contents>
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Long::class.java.classLoader) as? Long,
		parcel.readString(),
		parcel.readInt(),
		parcel.readString(),
		TODO("contents")
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(id)
		parcel.writeString(title)
		parcel.writeInt(position)
		parcel.writeString(type)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<CategoryItems> {
		override fun createFromParcel(parcel: Parcel): CategoryItems {
			return CategoryItems(parcel)
		}

		override fun newArray(size: Int): Array<CategoryItems?> {
			return arrayOfNulls(size)
		}
	}
}