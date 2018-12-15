package com.example.lugal.meetingmanagerjava

import com.example.lugal.meetingmanagerjava.database.AppDatabase
import android.app.Application
import com.example.lugal.meetingmanagerjava.di.*


class App : Application() {

    var database: AppDatabase? = null
        private set



    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .retrofitModule(RetrofitModule())
            .roomModule(RoomModule(this))
            .build()
        meetingRepositoryComponent = DaggerMeetingRepositoryComponent.create()
        meetingInteractorComponent = DaggerMeetingInteractorComponent.create()
        visitorsRepositoryComponent = DaggerVisitorsRepositoryComponent.create()
        visitorsInteractorComponent = DaggerVisitorsInteractorComponent.create()
    }

    companion object {
        lateinit var component: AppComponent
        lateinit var meetingInteractorComponent: MeetingInteractorComponent
        lateinit var meetingRepositoryComponent: MeetingRepositoryComponent
        lateinit var visitorsRepositoryComponent: VisitorsRepositoryComponent
        lateinit var visitorsInteractorComponent: VisitorsInteractorComponent
    }


}