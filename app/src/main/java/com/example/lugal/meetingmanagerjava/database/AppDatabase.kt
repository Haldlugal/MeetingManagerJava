package com.example.lugal.meetingmanagerjava.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity

@Database(entities = [MeetingEntity::class, VisitorEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meetingDao(): MeetingsDao
    abstract fun visitorDao(): VisitorsDao
}