package com.example.lugal.meetingmanagerjava.features.visitors.views.visitorsscreen

import android.util.Log
import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.Constants
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.BasePresenter
import com.example.lugal.meetingmanagerjava.features.visitors.domain.VisitorsInteractor
import com.example.lugal.meetingmanagerjava.network.PostResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VisitorsPresenter : BasePresenter<VisitorsListContract>(), VisitorsListPresenterContract{

    private val visitorsInteractor: VisitorsInteractor = App.visitorsInteractorComponent.getVisitorsInteractor()
    private val disposable = CompositeDisposable()

    private val TAG = "VisitorsPresenter"

    override fun viewIsReady() {
       deliverVisitors()
    }
    private fun deliverVisitors(){
        val meetingId = mvpView?.returnIntent()?.getIntExtra(Constants.EVENT_ID, -1)?:-1
        Log.d(TAG, meetingId.toString())
        val source = visitorsInteractor.getVisitors(meetingId)
        disposable.add(source
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError))
    }
    private fun handleResponse(visitorList: List<VisitorEntity>){
        mvpView!!.display(visitorList)
    }
    private fun handleError(e: Throwable){
        mvpView!!.displayError("Error fetching Visitor Data")
    }

    override fun destroy() {
        disposable.dispose()
    }

    fun export(visitors:List<VisitorEntity>){
        val meetingId = mvpView?.returnIntent()?.getIntExtra(Constants.EVENT_ID, -1)?:-1
        val source = visitorsInteractor.export(visitors, meetingId)
        disposable.add(source
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::exportSuccess, this::exportError))
    }

    private fun exportSuccess(response:List<PostResponse>){
        mvpView!!.showToast(response.size.toString())
    }
    private fun exportError(e:Throwable){
        mvpView!!.displayError("Error while exporting")
    }



    fun showVisitorInfo(visitor: VisitorEntity){
        mvpView?.showVisitorInfoScreen(visitor)
    }
    override fun checkVisitor(visitorId: Int) {
        visitorsInteractor.checkVisitor(visitorId)
    }

    override fun uncheckVisitor(visitorId: Int) {
        visitorsInteractor.uncheckVisitor(visitorId)
    }

    fun searchClicked(searchText : String){
        val source: Single<List<VisitorEntity>> = visitorsInteractor.getVisitorByNamePart(searchText)
        disposable.add(source
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError))
    }

}
