package com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorinfoscreen
import com.example.lugal.meetingmanagerjava.Constants
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.BasePresenter

class VisitorInfoActivityPresenter: BasePresenter<VisitorInfoContract>(), VisitorInfoPresenterContract{

    override fun viewIsReady() {
        val visitor = mvpView?.returnIntent()?.getSerializableExtra(Constants.VISITOR_FULL_INFO) as VisitorEntity
        mvpView!!.display(visitor)
    }
}
