package com.example.lugal.meetingmanagerjava.features.meetings.views
import android.util.Log
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.features.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import com.example.lugal.meetingmanagerjava.App
import com.example.lugal.meetingmanagerjava.features.meetings.domain.MeetingsInteractor


class MeetingsListPresenter : BasePresenter<MeetingListContract>(),
    MeetingListPresenterContract {

    private val meetingInteractor: MeetingsInteractor = App.meetingInteractorComponent.getMeetingInteractor()
    private val disposable = CompositeDisposable()

    private val TAG = "MeetingsListPresenter"

    override fun viewIsReady() {
        val source: Single<List<MeetingEntity>> = meetingInteractor.getMeetings()
        disposable.add(
            source
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }
    fun onCreate(){
    }
    private fun handleResponse(meetingList: List<MeetingEntity>){
        mvpView!!.display(meetingList)
    }
    private fun handleError(e: Throwable){
        Log.d(TAG, e.toString() + "happened")
        mvpView!!.displayError("Error fetching meeting Data")
    }

    override fun destroy() {
        disposable.dispose()
    }

}
