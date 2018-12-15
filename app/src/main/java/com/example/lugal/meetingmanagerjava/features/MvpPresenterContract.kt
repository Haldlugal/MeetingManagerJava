package com.example.lugal.meetingmanagerjava.features

interface MvpPresenterContract<T : MvpView> {
    fun attachView(mvpView: T)
    fun viewIsReady()
    fun detachView()
    fun destroy()
}
