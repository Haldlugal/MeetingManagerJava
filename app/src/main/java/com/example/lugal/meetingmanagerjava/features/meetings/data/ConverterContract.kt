package com.example.lugal.meetingmanagerjava.features.meetings.data

import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.models.MeetingModel
import com.example.lugal.meetingmanagerjava.models.VisitorModel

interface ConverterContract {
    fun convertToMeetingEntityList(meetingList: List<MeetingModel>):List<MeetingEntity>
    fun convertToVisitorEntityList(visitorsList: List<VisitorModel>, meetingId: Int):List<VisitorEntity>

}