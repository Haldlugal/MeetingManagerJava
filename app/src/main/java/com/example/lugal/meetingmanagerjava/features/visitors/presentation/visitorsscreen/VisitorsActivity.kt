package com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorsscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.lugal.meetingmanagerjava.Constants
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.features.visitors.presentation.adapters.VisitorsAdapter
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.visitors.presentation.visitorinfoscreen.VisitorInfoActivity
import kotlinx.android.synthetic.main.activity_visitors.*
import android.support.v7.app.AlertDialog

internal class VisitorsActivity : AppCompatActivity(), VisitorsListContract {
    private lateinit var adapter: VisitorsAdapter
    private lateinit var visitorsPresenter: VisitorsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitors)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setPresenter()
        adapter = VisitorsAdapter(visitorsPresenter)
        rvVisitors.adapter = adapter
        visitorsPresenter.viewIsReady()
        setSearchButtonListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        visitorsPresenter.detachView()
        if (isFinishing) {
            visitorsPresenter.destroy()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.export_button -> {
            visitorsPresenter.export(adapter.getVisitorsMet())
            true
        }

        R.id.meeting_stats_button -> {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.meeting_stat))
                .setMessage(getString(R.string.visitors) + adapter.getVisitorsMetCount().toString() + " " + getString(R.string.from) + " " + adapter.itemCount.toString())
                .setCancelable(false)
                .setNegativeButton(getString(R.string.ok)
                ) { dialog, _ -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setSearchButtonListener(){
        search_button.setOnClickListener {
            visitorsPresenter.searchClicked(search_field.text.toString())
        }
    }
    private fun setPresenter() {
        visitorsPresenter = VisitorsPresenter()
        visitorsPresenter.attachView(this)
    }

    override fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun display(visitorsResponse: List<VisitorEntity>) {
        adapter.setVisitorsList(visitorsResponse)
    }

    override fun displayError(errorText: String) {
        showToast(errorText)
    }
    override fun returnIntent(): Intent {
        return this.intent
    }
    override fun showVisitorInfoScreen(visitor : VisitorEntity) {
        VisitorInfoActivity.startVisitorsInfoActivityIntent(this, visitor)
    }

    companion object {
        fun startVisitorsActivityIntent(context: Context, meetingId: Int?){
            val intent = Intent(context, VisitorsActivity::class.java)
            intent.putExtra(Constants.EVENT_ID, meetingId)
            context.startActivity(intent)
        }
    }


}
