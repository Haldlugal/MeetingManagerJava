package com.example.lugal.meetingmanagerjava.features.visitors.data

import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import io.reactivex.Single

interface VisitorsRepositoryContract {
    fun loadVisitorsFromDb(meetingId: Int) : Single<List<VisitorEntity>>
    fun updateVisitor(visitorId:Int, visitorMet:Boolean)
    fun checkVisitor(visitorId: Int)
    fun uncheckVisitor(visitorId: Int)
    fun getVisitorByNamePart(namePart: String) : Single<List<VisitorEntity>>
    fun countVisitorsMet(meetingId: Int) : Single<List<Int>>
    fun countVisitorsTotal(meetingId: Int) : Single<List<Int>>
}