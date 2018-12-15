package com.example.lugal.meetingmanagerjava.features.meetings.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.features.meetings.views.adapters.MeetingsAdapter
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.features.visitors.views.visitorsscreen.VisitorsActivity

class MeetingsListActivity : AppCompatActivity(),
    MeetingListContract {

    private lateinit var rvMeetings: RecyclerView
    private val TAG = "MeetingsListActivity"
    private lateinit var meetingsListPresenter: MeetingsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)
        rvMeetings = findViewById(R.id.rvMeetings)
        setPresenter()
        meetingsListPresenter.onCreate()
        setupViews()
        meetingsListPresenter.viewIsReady()
    }


    override fun onDestroy() {
        super.onDestroy()
        meetingsListPresenter.detachView()
        if (isFinishing) {
            meetingsListPresenter.destroy()
        }
    }

    private fun setPresenter() {
        meetingsListPresenter = MeetingsListPresenter()
        meetingsListPresenter.attachView(this)
    }

    private fun setupViews() {
        rvMeetings.layoutManager = LinearLayoutManager(this)
    }

    fun showToast(str: String) {
        Toast.makeText(this@MeetingsListActivity, str, Toast.LENGTH_LONG).show()
    }

    override fun display(meetingResponse: List<MeetingEntity>) {
        if (meetingResponse != null) {
            for (i in meetingResponse.indices) {
                Log.d(TAG, meetingResponse[i].title)
            }

            rvMeetings.adapter = MeetingsAdapter(
                meetingResponse
            ) {
                showToast("${it.title} Clicked")
                VisitorsActivity.startVisitorsActivityIntent(this, it.id)
            }
        } else {
            Log.d(TAG, "Meetings response null")
        }
    }

    override fun displayError(error: String) {
        showToast(error)
    }

}
