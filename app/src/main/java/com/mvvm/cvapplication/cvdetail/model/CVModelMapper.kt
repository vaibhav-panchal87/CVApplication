package com.mvvm.cvapplication.cvdetail.model

import com.mvvm.cvapplication.data.remote.model.CVResponse
import com.mvvm.cvapplication.util.BaseMapper
import javax.inject.Inject

/**
 * Purpose is to convert API response object to CV Model for UI
 * thus filtering null objects from server
 * */
class CVModelMapper @Inject constructor() : BaseMapper<CVResponse, CVModel> {
    override fun convert(input: CVResponse): CVModel {

        val projects = ArrayList<Projects>()

        var address = ""

        var skills = ""

        // Combing Address object into one
        input.address?.let {
            address = "${it.streetAddress} ${it.city} ${it.state}-${it.zipOrPostal}"
        }

        input.skills.forEach {
            skills += it + "\n"
        }

        input.projects.forEach {
            projects.add(
                    Projects(
                            it.name,
                            it.start,
                            it.end,
                            it.type,
                            it.role,
                            it.details,
                            it.environment
                    )
            )
        }
        return CVModel(
                "${input.firstName} ${input.lastName}",
                input.gender,
                input.dob,
                input.email,
                input.profilePic,
                address,
                input.phoneNumber,
                input.summary,
                skills,
                projects
        )
    }

}