package com.example.lugal.meetingmanagerjava.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "visitors")
class VisitorEntity (
    @PrimaryKey
    val id: Int,
    val addition: String,
    val agreedByManager: String,
    val city: String,
    val company: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val phone: String,
    val position: String,
    val registeredDate: String,
    val eventId: Int,
    var meetingVisited: Boolean = false
) : Serializable