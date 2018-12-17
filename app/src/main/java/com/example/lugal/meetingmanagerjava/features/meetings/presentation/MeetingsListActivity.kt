package com.example.lugal.meetingmanagerjava.features.meetings.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.features.meetings.presentation.adapters.MeetingsAdapter
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorsscreen.VisitorsActivity

class MeetingsListActivity : AppCompatActivity(),
    MeetingListContract {
    private lateinit var adapter: MeetingsAdapter
    private lateinit var rvMeetings: RecyclerView
    private lateinit var meetingsListPresenter: MeetingsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)
        rvMeetings = findViewById(R.id.rvMeetings)
        setPresenter()
        adapter = MeetingsAdapter(meetingsListPresenter)
        rvMeetings.adapter = adapter
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

    private fun showToast(str: String) {
        Toast.makeText(this@MeetingsListActivity, str, Toast.LENGTH_LONG).show()
    }

    override fun display(meetingResponse: List<MeetingEntity>) {
        adapter.setMeetingsList(meetingResponse)
    }

    override fun startVisitorsActivity(eventId:Int){
        VisitorsActivity.startVisitorsActivityIntent(this, eventId)
    }

    override fun displayError(errorText: String) {
        showToast(errorText)
    }

}
