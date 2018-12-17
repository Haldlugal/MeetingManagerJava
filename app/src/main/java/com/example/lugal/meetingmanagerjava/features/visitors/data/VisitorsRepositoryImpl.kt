package com.example.lugal.meetingmanagerjava.features.visitors.data

import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.database.AppDatabase
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.visitors.domain.VisitorsRepository
import com.example.lugal.meetingmanagerjava.network.PostResponse
import com.example.lugal.meetingmanagerjava.network.VisitorPost
import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class VisitorsRepositoryImpl : VisitorsRepository {

    @Inject
    lateinit var database : AppDatabase
    @Inject
    lateinit var retrofit: Retrofit

    init {
        App.component.injectsVisitorsRepository(this)
    }

    override fun loadVisitorsFromDb(meetingId : Int): Single<List<VisitorEntity>> {
        return database.visitorDao().getVisitorsList(meetingId)
    }

    override fun updateVisitor(visitorId : Int, visitorMet : Boolean) {
        Completable.fromAction { run {
            database.visitorDao().updateVisitorMet(visitorId, visitorMet)
        } }.subscribeOn(Schedulers.io()).doOnComplete{run{
        }}.subscribe()
    }

    override fun checkVisitor(visitorId: Int) {
        updateVisitor(visitorId, true)
    }

    override fun uncheckVisitor(visitorId: Int) {
        updateVisitor(visitorId, false)
    }

    override fun getVisitorByNamePart(namePart: String) : Single<List<VisitorEntity>>{
        return database.visitorDao().selectVisitorsByNames(namePart)
    }

    override fun countVisitorsMet(meetingId: Int): Single<List<Int>> {
        return database.visitorDao().countVisitorsMet(meetingId)
    }

    override fun countVisitorsTotal(meetingId: Int): Single<List<Int>> {
        return database.visitorDao().countVisitorsTotal(meetingId)
    }

    override fun export(visitors : List<VisitorEntity>, eventId: Int):Single<List<PostResponse>>{
        return retrofit
            .create(com.example.lugal.meetingmanagerjava.network.NetworkInterface::class.java)
            .sendVisitor(convertVisitorsToVisitorPost(visitors), eventId)
    }

    private fun convertVisitorsToVisitorPost(visitors:List<VisitorEntity>) : List<VisitorPost>{
        return visitors.map { it-> VisitorPost(it.id, it.meetingVisited, "date stub")}
    }

    @Provides
    @Singleton
    fun provideVisitorsRepository(): VisitorsRepository {
        return VisitorsRepositoryImpl()
    }
}