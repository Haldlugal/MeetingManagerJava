package com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorinfoscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.Toast
import com.example.lugal.meetingmanagerjava.Constants
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import kotlinx.android.synthetic.main.visitor_info.*

class VisitorInfoActivity : AppCompatActivity(), VisitorInfoContract{

    private lateinit var visitorInfoPresenter: VisitorInfoActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visitor_info)
        setPresenter()
        visitorInfoPresenter.viewIsReady()
    }

    private fun setPresenter() {
        visitorInfoPresenter = VisitorInfoActivityPresenter()
        visitorInfoPresenter.attachView(this)
    }

    override fun display(visitor: VisitorEntity) {
        firstName.text = visitor.firstName
        secondName.text = visitor.lastName
        patronymic.text = visitor.patronymic
        city.text = visitor.city
        company.text = visitor.company
        phone.text = visitor.phone
        val additionalText = visitor.addition.replace("\n", "<br>")
        additional.text = Html.fromHtml(additionalText)
    }

    override fun displayError(errorText: String) {
        showToast(errorText)
    }

    override fun returnIntent(): Intent {
        return this.intent
    }

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun startVisitorsInfoActivityIntent(context: Context, visitor: VisitorEntity?){
            val intent = Intent(context, VisitorInfoActivity::class.java)
            intent.putExtra(Constants.VISITOR_FULL_INFO, visitor)
            context.startActivity(intent)
        }
    }

}
