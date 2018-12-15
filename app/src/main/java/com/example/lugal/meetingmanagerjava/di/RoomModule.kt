package com.example.lugal.meetingmanagerjava.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.lugal.meetingmanagerjava.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(var context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
}