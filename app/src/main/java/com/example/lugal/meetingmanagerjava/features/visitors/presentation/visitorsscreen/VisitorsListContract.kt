package com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorsscreen

import android.content.Intent
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.MvpView

interface VisitorsListContract : MvpView {
    fun display(visitorsResponse: List<VisitorEntity>)
    fun displayError(errorText: String)
    fun returnIntent() : Intent
    fun showVisitorInfoScreen(visitor : VisitorEntity)
    fun showToast(string: String)
}