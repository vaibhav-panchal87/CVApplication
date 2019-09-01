package com.mvvm.cvapplication.data.remote.model
data class CVResponse (

	val firstName : String,
	val lastName : String,
	val gender : String,
	val dob : String,
	val email : String,
	val profilePic : String,
	val address : Address? = null,
	val phoneNumber : String,
	val summary : String,
	val skills : List<String>,
	val projects : List<Projects>
)

data class Projects (

	val name : String,
	val start : String,
	val end : String,
	val type : String,
	val role : String,
	val details : String,
	val environment : String
)

data class Address (

	val streetAddress : String,
	val city : String,
	val state : String,
	val zipOrPostal : Int
)