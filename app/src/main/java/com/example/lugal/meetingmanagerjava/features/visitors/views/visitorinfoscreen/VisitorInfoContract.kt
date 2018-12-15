package com.example.lugal.meetingmanagerjava.features.visitors.views.visitorinfoscreen

import android.content.Intent
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.MvpView

interface VisitorInfoContract : MvpView {
    fun display(visitor: VisitorEntity)
    fun displayError(errorText: String)
    fun returnIntent() : Intent
}