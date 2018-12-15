package com.example.lugal.meetingmanagerjava.database

import android.arch.persistence.room.*
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import io.reactivex.Single


@Dao
interface MeetingsDao {
    @Insert
    fun insertMeetings( meetings: List<MeetingEntity>)

    @Query("SELECT * FROM meetings")
    fun getMeetingsList(): List<MeetingEntity>

    @Query("DELETE FROM meetings")
    fun deleteMeetings()
}