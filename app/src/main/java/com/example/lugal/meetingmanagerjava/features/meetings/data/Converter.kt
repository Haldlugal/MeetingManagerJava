package com.example.lugal.meetingmanagerjava.features.meetings.data
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.models.MeetingModel
import com.example.lugal.meetingmanagerjava.models.VisitorModel
import dagger.Module
import java.text.SimpleDateFormat
import java.util.*
import dagger.Provides
import javax.inject.Singleton


@Module
class Converter : ConverterContract{

    override fun convertToMeetingEntityList(meetingList: List<MeetingModel>):List<MeetingEntity>{
        return meetingList.map{convertToMeetingEntity(it)}
    }

    override fun convertToVisitorEntityList(visitorsList: List<VisitorModel>, meetingId: Int):List<VisitorEntity>{
        return visitorsList.map { convertToVisitorEntity(it, meetingId) }
    }


    private fun convertToMeetingEntity(meeting: MeetingModel): MeetingEntity {
        return MeetingEntity(
            id = meeting.id,
            title = meeting.title,
            description = meeting.description,
            beginDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(meeting.date.start).time,
            endDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(meeting.date.end).time,
            cardImage = meeting.cardImage,
            eventFormat = meeting.eventFormat
        )
    }

    private fun convertToVisitorEntity(visitor: VisitorModel, meetingId: Int): VisitorEntity {
        return VisitorEntity(
            addition = visitor.addition,
            agreedByManager = visitor.agreedByManager,
            city = visitor.city,
            company = visitor.company,
            email = visitor.email,
            firstName = visitor.firstName,
            id = visitor.id,
            lastName = visitor.lastName,
            patronymic = visitor.patronymic,
            phone = visitor.phone,
            position = visitor.position,
            registeredDate = visitor.registeredDate,
            eventId = meetingId,
            meetingVisited = false
        )
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter {
        return Converter()
    }
}