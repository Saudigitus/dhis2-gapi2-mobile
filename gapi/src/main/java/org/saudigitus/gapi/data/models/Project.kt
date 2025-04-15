package org.saudigitus.gapi.data.models

data class Project(
    val title: String,
    val duration: String,
    val financier: String,
    val implementer: String,
    val submittedApplication: String,
    val approvedApplication: String,
) {
    override fun toString(): String {
        return title
    }
}
