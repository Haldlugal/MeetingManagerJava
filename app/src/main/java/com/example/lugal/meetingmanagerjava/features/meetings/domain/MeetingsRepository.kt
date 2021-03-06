package com.example.lugal.meetingmanagerjava.features.meetings.domain

import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import io.reactivex.Single

interface MeetingsRepository {
    fun loadMeetings(): Single<List<MeetingEntity>>
}