package com.example.lugal.meetingmanagerjava.features.visitors.domain

import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import io.reactivex.Single

interface VisitorsInteractorContract {
    fun getVisitors(meetingid : Int): Single<List<VisitorEntity>>
    fun checkVisitor(visitorId: Int)
    fun uncheckVisitor(visitorId: Int)
    fun getVisitorByNamePart(namePart: String): Single<List<VisitorEntity>>
    fun countVisitorsMet(meetingId: Int) : Single<List<Int>>
    fun countVisitorsTotal(meetingId: Int) : Single<List<Int>>
}