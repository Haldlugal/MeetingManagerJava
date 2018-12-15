package com.example.lugal.meetingmanagerjava.features.meetings.data

import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import io.reactivex.Single

interface MeetingsRepositoryContract {
    fun loadMeetings(): Single<List<MeetingEntity>>
}