package com.example.lugal.meetingmanagerjava.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import io.reactivex.Single

@Dao
interface VisitorsDao {

    @Query("SELECT * FROM visitors WHERE eventId = :meetingId")
    fun getVisitorsList(meetingId : Int): Single<List<VisitorEntity>>

    @Query("UPDATE visitors SET meetingVisited = :wasMet WHERE id = :visitorId")
    fun updateVisitorMet(visitorId : Int, wasMet: Boolean)

    @Insert
    fun insertVisitors( visitors : List<VisitorEntity>)

    @Query("DELETE FROM visitors")
    fun deleteVisitors()

    @Query("SELECT * FROM visitors WHERE firstName LIKE '%' || :namePart || '%' OR lastName LIKE '%' || :namePart || '%' OR patronymic LIKE '%' || :namePart || '%'")
    fun selectVisitorsByNames(namePart : String) : Single<List<VisitorEntity>>

    @Query("SELECT COUNT(*) FROM visitors WHERE meetingVisited = 'true' AND eventId = :meetingId")
    fun countVisitorsMet(meetingId: Int) : Single<List<Int>>

    @Query("SELECT COUNT(*) FROM visitors WHERE eventId = :meetingId")
    fun countVisitorsTotal(meetingId: Int) : Single<List<Int>>

}