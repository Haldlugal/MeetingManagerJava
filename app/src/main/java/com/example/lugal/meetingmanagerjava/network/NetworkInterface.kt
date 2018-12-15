package com.example.lugal.meetingmanagerjava.network

import com.example.lugal.meetingmanagerjava.models.MeetingModel
import com.example.lugal.meetingmanagerjava.models.VisitorModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkInterface {
    @GET("/api/v1/Events/registration")
    fun getMeetings(): Single<List<MeetingModel>>

    @GET(" /api/v1/Registration/members/event/{eventId}?token=cftteamtest2018")
    fun getVisitors(@Path("eventId") eventId: Int?): Single<List<VisitorModel>>

    @POST("/api/v1/Registration/members/event/{eventId}/confirmation?token=cftteamtest2018")
    fun sendVisitor(@Body visitors: List<VisitorPost>, @Path("eventId") eventId: Int? ) : Single<List<PostResponse>>
}
