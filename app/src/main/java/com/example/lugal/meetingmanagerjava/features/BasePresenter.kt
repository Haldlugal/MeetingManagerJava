package com.example.lugal.meetingmanagerjava.features

abstract class BasePresenter<T : MvpView> :
    MvpPresenterContract<T> {
    var mvpView: T? = null
        private set

    protected val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun viewIsReady() {

    }

    override fun detachView() {
        mvpView = null
    }

    override fun destroy() {

    }

}
