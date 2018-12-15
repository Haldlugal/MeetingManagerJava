package com.example.lugal.meetingmanagerjava.di


import com.example.lugal.meetingmanagerjava.features.meetings.data.Converter
import com.example.lugal.meetingmanagerjava.features.meetings.data.MeetingsRepository
import com.example.lugal.meetingmanagerjava.features.meetings.domain.MeetingsInteractor
import com.example.lugal.meetingmanagerjava.features.visitors.data.VisitorsRepository
import com.example.lugal.meetingmanagerjava.features.visitors.domain.VisitorsInteractor
import dagger.Component


import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, Converter::class, RoomModule::class])
interface AppComponent{
    fun injectsMeetingsRepository(meetingsRepository : MeetingsRepository)
    fun injectsVisitorsRepository(visitorsRepository: VisitorsRepository)
}

@Singleton
@Component(modules=[MeetingsInteractor::class])
interface MeetingInteractorComponent{
    fun getMeetingInteractor() : MeetingsInteractor
}
@Singleton
@Component(modules = [MeetingsRepository::class])
interface MeetingRepositoryComponent{
    fun getMeetingsRepository() : MeetingsRepository
}
@Singleton
@Component(modules = [VisitorsRepository::class])
interface VisitorsRepositoryComponent{
    fun getVisitorsRepository() : VisitorsRepository
}
@Singleton
@Component(modules=[VisitorsInteractor::class])
interface VisitorsInteractorComponent{
    fun getVisitorsInteractor() : VisitorsInteractor
}
