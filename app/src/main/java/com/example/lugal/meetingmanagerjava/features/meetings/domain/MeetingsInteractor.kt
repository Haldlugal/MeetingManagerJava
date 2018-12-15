package com.example.lugal.meetingmanagerjava.features.meetings.domain

import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.features.meetings.data.MeetingsRepository
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class MeetingsInteractor : MeetingsInteractorContract{

    private val repository: MeetingsRepository = App.meetingRepositoryComponent.getMeetingsRepository()

    override fun getMeetings(): Single<List<MeetingEntity>> {
        return repository.loadMeetings()
    }

    @Provides
    fun provideMeetingsInteractor(): MeetingsInteractor {
        return MeetingsInteractor()
    }


}