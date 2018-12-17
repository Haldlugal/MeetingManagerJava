package com.example.lugal.meetingmanagerjava.features.visitors.presentation.adapters
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import kotlinx.android.synthetic.main.row_visitors.view.*
import com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorsscreen.VisitorsPresenter


class VisitorsAdapter (private val visitorsPresenter: VisitorsPresenter ) :
    RecyclerView.Adapter<VisitorsAdapter.VisitorHolder>(){
    private var visitorsList: List<VisitorEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VisitorHolder(parent.inflate(R.layout.row_visitors))

    override fun onBindViewHolder(holder: VisitorHolder, position: Int) = holder.bind(visitorsList!![position])

    override fun getItemCount() = visitorsList?.size ?:0

    fun getVisitorsMetCount() : Int {
        return visitorsList?.filter {
            it.meetingVisited
        }?.size ?:0
    }

    fun getVisitorsMet() : List<VisitorEntity>{
        return visitorsList!!.filter {
            it.meetingVisited
        }
    }

    fun setVisitorsList(visitors : List<VisitorEntity>){
        visitorsList = visitors
        notifyDataSetChanged()
    }

    inner class VisitorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(visitor: VisitorEntity) = with(itemView) {
            visitorFirstName.text = visitor.firstName
            visitorPatronymic.text = visitor.patronymic
            visitorSecondName.text = visitor.lastName
            visitorPhone.text = visitor.phone
            meetingSwitch.isChecked = visitor.meetingVisited
            meetingSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    visitor.meetingVisited = true
                    visitorsPresenter.checkVisitor(visitor.id)
                } else {
                    visitor.meetingVisited = false
                    visitorsPresenter.uncheckVisitor(visitor.id)
                }
            }
            setOnClickListener {
                visitorsPresenter.showVisitorInfo(visitor)
            }

        }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

}