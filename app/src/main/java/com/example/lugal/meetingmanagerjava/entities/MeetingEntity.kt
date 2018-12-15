package com.example.lugal.meetingmanagerjava.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "meetings")
data class MeetingEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val beginDate: Long,
    val endDate: Long,
    val cardImage: String,
    val eventFormat: String
)