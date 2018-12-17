package com.example.lugal.meetingmanagerjava.di


import com.example.lugal.meetingmanagerjava.features.meetings.data.Converter
import com.example.lugal.meetingmanagerjava.features.meetings.data.MeetingsRepositoryImpl
import com.example.lugal.meetingmanagerjava.features.meetings.domain.MeetingsInteractor
import com.example.lugal.meetingmanagerjava.features.meetings.domain.MeetingsInteractorImpl
import com.example.lugal.meetingmanagerjava.features.meetings.domain.MeetingsRepository
import com.example.lugal.meetingmanagerjava.features.visitors.data.VisitorsRepositoryImpl
import com.example.lugal.meetingmanagerjava.features.visitors.domain.VisitorsInteractor
import com.example.lugal.meetingmanagerjava.features.visitors.domain.VisitorsInteractorImpl
import com.example.lugal.meetingmanagerjava.features.visitors.domain.VisitorsRepository
import dagger.Component


import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, Converter::class, RoomModule::class])
interface AppComponent{
    fun injectsMeetingsRepository(meetingsRepositoryImpl : MeetingsRepositoryImpl)
    fun injectsVisitorsRepository(visitorsRepositoryImpl: VisitorsRepositoryImpl)
}

@Singleton
@Component(modules=[MeetingsInteractorImpl::class])
interface MeetingInteractorComponent{
    fun getMeetingInteractor() : MeetingsInteractor
}
@Singleton
@Component(modules = [MeetingsRepositoryImpl::class])
interface MeetingRepositoryComponent{
    fun getMeetingsRepository() : MeetingsRepository
}
@Singleton
@Component(modules = [VisitorsRepositoryImpl::class])
interface VisitorsRepositoryComponent{
    fun getVisitorsRepository() : VisitorsRepository
}
@Singleton
@Component(modules=[VisitorsInteractorImpl::class])
interface VisitorsInteractorComponent{
    fun getVisitorsInteractor() : VisitorsInteractor
}
