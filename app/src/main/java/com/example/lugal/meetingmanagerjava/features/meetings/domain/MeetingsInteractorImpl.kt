package com.example.lugal.meetingmanagerjava.features.meetings.domain

import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import dagger.Module
import dagger.Provides
import io.reactivex.Single

@Module
class MeetingsInteractorImpl : MeetingsInteractor{

    private val repositoryImpl = App.meetingRepositoryComponent.getMeetingsRepository()

    override fun getMeetings(): Single<List<MeetingEntity>> {
        return repositoryImpl.loadMeetings()
    }

    @Provides
    fun provideMeetingsInteractor(): MeetingsInteractor {
        return MeetingsInteractorImpl()
    }


}