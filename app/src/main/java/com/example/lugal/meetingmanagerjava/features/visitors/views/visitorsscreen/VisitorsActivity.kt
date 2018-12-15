package com.example.lugal.meetingmanagerjava.features.visitors.views.visitorsscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.lugal.meetingmanagerjava.Constants
import com.example.lugal.meetingmanagerjava.R
import com.example.lugal.meetingmanagerjava.features.visitors.views.adapters.VisitorsAdapter
import com.example.lugal.meetingmanagerjava.entities.VisitorEntity
import com.example.lugal.meetingmanagerjava.features.visitors.views.visitorinfoscreen.VisitorInfoActivity
import kotlinx.android.synthetic.main.activity_visitors.*
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

internal class VisitorsActivity : AppCompatActivity(), VisitorsListContract {
    private lateinit var adapter: VisitorsAdapter
    private lateinit var visitorsPresenter: VisitorsPresenter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitors)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        setPresenter()
        setupViews()
        adapter = VisitorsAdapter(
            visitorsPresenter
        )
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
            showToast(adapter.getVisitorsMetCount().toString())
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Статистика встречи")
                .setMessage("Посетителей: " + adapter.getVisitorsMetCount().toString() + " из " + adapter.itemCount.toString())
                .setCancelable(false)
                .setNegativeButton("ОК",
                    DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
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
            Log.d("Click!" , search_field.text.toString())
            visitorsPresenter.searchClicked(search_field.text.toString())
        }
    }
    private fun setPresenter() {
        visitorsPresenter = VisitorsPresenter()
        visitorsPresenter.attachView(this)
    }

    private fun setupViews() {
        linearLayoutManager = LinearLayoutManager(this)
        rvVisitors.layoutManager = linearLayoutManager
    }

    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun display(visitorsResponse: List<VisitorEntity>) {
        adapter.setVisitorsList(visitorsResponse)
    }

    override fun displayError(error: String) {
        showToast(error)
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
