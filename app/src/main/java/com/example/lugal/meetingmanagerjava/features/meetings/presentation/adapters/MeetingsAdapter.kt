package com.example.lugal.meetingmanagerjava.features.meetings.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import com.example.lugal.meetingmanagerjava.features.meetings.presentation.MeetingsListPresenter
import kotlinx.android.synthetic.main.row_meetings.view.*

class MeetingsAdapter(private val meetingPresenter: MeetingsListPresenter ) :
    RecyclerView.Adapter<MeetingsAdapter.MeetingsHolder>() {
    private var meetingsList: List<MeetingEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeetingsHolder(parent.inflate(R.layout.row_meetings))

    override fun onBindViewHolder(holder: MeetingsHolder, position: Int) = holder.bind(meetingsList!![position])

    override fun getItemCount() = meetingsList?.size?:0

    fun setMeetingsList(meetings : List<MeetingEntity>){
        Log.d("meetingAdapter", meetings.size.toString())
        meetingsList = meetings
        notifyDataSetChanged()
    }

    inner class MeetingsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(meeting: MeetingEntity) = with(itemView) {
            meetingTitle.text = meeting.title
            meetingPlace.text = meeting.description
            meetingType.text = meeting.eventFormat
            Glide.with(context).load("https://beta-team.cft.ru" + meeting.cardImage).into(meetingImage)
            setOnClickListener {
                meetingPresenter.showVisitors(meeting.id)
            }
        }
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}