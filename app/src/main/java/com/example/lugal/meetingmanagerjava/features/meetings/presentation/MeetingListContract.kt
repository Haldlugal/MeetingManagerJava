package com.example.lugal.meetingmanagerjava.features.meetings.presentation

import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.features.MvpView

interface MeetingListContract : MvpView {
    fun display(meetingResponse: List<MeetingEntity>)
    fun displayError(errorText: String)
    fun startVisitorsActivity(eventId: Int)
}
