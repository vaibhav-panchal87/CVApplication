package com.mvvm.cvapplication.cvdetail.model

import android.os.Parcel
import android.os.Parcelable

data class CVModel(
        val fullName: String,
        val gender: String,
        val dob: String,
        val email: String,
        val profilePic: String,
        val address: String,
        val phoneNumber: String,
        val summary: String,
        val skills: String,
        val projects: List<Projects>
)


data class Projects(

    val name: String?,
    val start: String?,
    val end: String?,
    val type: String?,
    val role: String?,
    val details: String?,
    val environment: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(start)
        parcel.writeString(end)
        parcel.writeString(type)
        parcel.writeString(role)
        parcel.writeString(details)
        parcel.writeString(environment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Projects> {
        override fun createFromParcel(parcel: Parcel): Projects {
            return Projects(parcel)
        }

        override fun newArray(size: Int): Array<Projects?> {
            return arrayOfNulls(size)
        }
    }
}
