package com.example.lugal.meetingmanagerjava.features.visitors.domain

import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.network.PostResponse
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton

@Module
class VisitorsInteractorImpl : VisitorsInteractor{
    private val visitorsRepository = App.visitorsRepositoryComponent.getVisitorsRepository()

    override fun getVisitors(meetingid : Int): Single<List<VisitorEntity>> {
        return visitorsRepository.loadVisitorsFromDb(meetingid)
    }

    override fun checkVisitor(visitorId: Int) {
        visitorsRepository.checkVisitor(visitorId)
    }

    override fun uncheckVisitor(visitorId: Int) {
        visitorsRepository.uncheckVisitor(visitorId)
    }

    override fun getVisitorByNamePart(namePart: String) : Single<List<VisitorEntity>>{
        return visitorsRepository.getVisitorByNamePart(namePart)
    }

    override fun countVisitorsMet(meetingId: Int): Single<List<Int>> {
        return visitorsRepository.countVisitorsMet(meetingId)
    }

    override fun countVisitorsTotal(meetingId: Int): Single<List<Int>> {
        return visitorsRepository.countVisitorsTotal(meetingId)
    }

    override fun export(visitors : List<VisitorEntity>, meetingId: Int):Single<List<PostResponse>>{
        return visitorsRepository.export(visitors, meetingId)
    }

    @Singleton
    @Provides
    fun provideMeetingsInteractor(): VisitorsInteractor {
        return VisitorsInteractorImpl()
    }
}