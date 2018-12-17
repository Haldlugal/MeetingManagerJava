package com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorsscreen
import com.example.lugal.meetingmanagerjava.features.MvpPresenterContract


interface VisitorsListPresenterContract : MvpPresenterContract<VisitorsListContract> {
    fun checkVisitor(visitorId: Int)
    fun uncheckVisitor(visitorId: Int)
}