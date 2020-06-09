package com.ijikod.sensyne.Model


data class Hospital (
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
    val latitude: Double?,
    val longitude: Double,
    val parentODSCode: String?,
    val parentName: String?,
    val phone: String?,
    val email: String?,
    val website: String?,
    val fax: String?
)