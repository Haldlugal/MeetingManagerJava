package com.example.lugal.meetingmanagerjava.features.meetings.data

import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.database.AppDatabase
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.meetings.domain.MeetingsRepository
import com.example.lugal.meetingmanagerjava.network.NetworkInterface
import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class MeetingsRepositoryImpl : MeetingsRepository {

    @Inject
    lateinit var retrofit : Retrofit
    @Inject
    lateinit var converter : Converter
    @Inject
    lateinit var database : AppDatabase

    init {
        App.component.injectsMeetingsRepository(this)
    }

    override fun loadMeetings(): Single<List<MeetingEntity>> {
        return retrofit
            .create(NetworkInterface::class.java)
            .getMeetings()
            .map { meetingList->converter.convertToMeetingEntityList(meetingList) }
            .doOnSuccess { meetingList -> run {
                refreshMeetingsListInDb(meetingList)
            deleteVisitorsFromDb()
                    .doOnComplete{
                    meetingList.forEach { meeting -> run {
                        loadVisitorsFromNet(meeting.id)
                    } }
                }.subscribe()

            }}
            .onErrorReturn {
                loadMeetingsFromDb()
            }
    }

    private fun refreshMeetingsListInDb(meetingsList: List<MeetingEntity>){
        deleteMeetingsFromDb().doOnComplete{insertMeetingsIntoDb(meetingsList)}.subscribe()
    }

    private fun insertMeetingsIntoDb(meetingsList: List<MeetingEntity>){
        Completable
            .fromAction { run {
                database.meetingDao().insertMeetings(meetingsList)
            } }
            .subscribeOn(Schedulers.io()).subscribe()
    }

    private fun deleteMeetingsFromDb() : Completable{
        return Completable.fromAction { run{
            database.meetingDao().deleteMeetings()
        } }.subscribeOn(Schedulers.io())

    }

    private fun loadMeetingsFromDb():List<MeetingEntity> {
        return database.meetingDao().getMeetingsList()
    }

    private fun deleteVisitorsFromDb() : Completable {
        return Completable.fromAction{ run {
            database.visitorDao().deleteVisitors()
        }}.subscribeOn(Schedulers.io())
    }

    private fun insertVisitorsIntoDb(visitorsList: List<VisitorEntity>){
        Completable.fromAction { run {
            database.visitorDao().insertVisitors(visitorsList)
        } }.subscribeOn(Schedulers.io()).subscribe()
    }

    private fun loadVisitorsFromNet(meetingId: Int) {
        retrofit.create(NetworkInterface::class.java)
            .getVisitors(meetingId)
            .map { visitorsList->converter.convertToVisitorEntityList(visitorsList, meetingId) }
            .doOnSuccess { visitorsList->insertVisitorsIntoDb(visitorsList) }
            .subscribeOn( Schedulers.io())
            .subscribe()
    }



    @Provides
    @Singleton
    fun provideMeetingsRepository(): MeetingsRepository {
        return MeetingsRepositoryImpl()
    }
}