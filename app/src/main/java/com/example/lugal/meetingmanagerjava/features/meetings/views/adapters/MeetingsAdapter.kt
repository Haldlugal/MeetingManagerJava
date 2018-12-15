package com.example.lugal.meetingmanagerjava.features.meetings.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.entities.MeetingEntity
import kotlinx.android.synthetic.main.row_meetings.view.*

class MeetingsAdapter(private val meetingsList: List<MeetingEntity>, private val listener: (MeetingEntity) -> Unit) :
    RecyclerView.Adapter<MeetingsAdapter.MeetingsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeetingsHolder(parent.inflate(R.layout.row_meetings))

    override fun onBindViewHolder(holder: MeetingsHolder, position: Int) = holder.bind(meetingsList[position], listener)

    override fun getItemCount() = meetingsList.size

    inner class MeetingsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(meeting: MeetingEntity, listener: (MeetingEntity) -> Unit) = with(itemView) {
            tvTitle.text = meeting.title
            tvOverView.text = meeting.description
            tvReleaseDate.text = meeting.eventFormat
            Glide.with(context).load("https://beta-team.cft.ru" + meeting.cardImage!!).into(ivMovie)
            setOnClickListener { listener(meeting) }
        }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}