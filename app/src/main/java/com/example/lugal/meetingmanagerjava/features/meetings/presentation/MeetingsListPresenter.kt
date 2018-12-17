package com.example.lugal.meetingmanagerjava.features.meetings.presentation
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.features.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import com.example.lugal.meetingmanagerjava.App



class MeetingsListPresenter : BasePresenter<MeetingListContract>(),
    MeetingListPresenterContract {

    private val meetingInteractorImpl = App.meetingInteractorComponent.getMeetingInteractor()
    private val disposable = CompositeDisposable()


    override fun viewIsReady() {
        val source: Single<List<MeetingEntity>> = meetingInteractorImpl.getMeetings()
        disposable.add(
            source
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }


    fun showVisitors(eventId: Int){
        mvpView!!.startVisitorsActivity(eventId)
    }
    private fun handleResponse(meetingList: List<MeetingEntity>){
        mvpView!!.display(meetingList)
    }
    private fun handleError(e: Throwable){
        mvpView!!.displayError(e.toString())
    }

    override fun destroy() {
        disposable.dispose()
    }

}
