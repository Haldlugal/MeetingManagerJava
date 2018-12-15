package com.example.lugal.meetingmanagerjava.models

data class MeetingModel (
    val id: Int,
    val title: String,
    val cities: List<City>,
    val description: String,
    val format: Int,
    val date: Date,
    val cardImage: String,
    val status: Int,
    val iconStatus: String,
    val eventFormat: String,
    val eventFormatEng: String
)
data class Date (
    val start: String,
    val end: String
)
data class City (
    val id: Int,
    val nameRus: String,
    val nameEng: String,
    val icon: String,
    val isActive: Boolean
)

