package com.ijikod.sensyne.Model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "Hospitals")
data class Hospital (
    @PrimaryKey(autoGenerate = true)
    val uid : Int = 0,
    val organisationID: Long,
    val organisationCode: String?,
    val organisationType: String?,
    val subType: String?,
    val sector: String?,
    val organisationStatus: String?,
    val isPimsManaged: String?,
    val organisationName: String?,
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String?,
    val county: String?,
    val postcode: String?,
    val latitude: String?,
    val longitude: String,
    val parentODSCode: String?,
    val parentName: String?,
    val phone: String?,
    val email: String?,
    val website: String?,
    val fax: String?
):Parcelable {

    fun getInitials() : String?{
        val orgName =  if (this.organisationName?.split(" ")?.size == 1)  "${this.organisationName.split(" ").get(0)}" else "${this.organisationName?.split(" ")?.get(0)} ${this.organisationName?.split(" ")?.get(1)}"
       return orgName
            ?.split(' ')
            ?.mapNotNull { it.firstOrNull()?.toString() }
            ?.reduce { acc, s -> acc + s }
    }
}