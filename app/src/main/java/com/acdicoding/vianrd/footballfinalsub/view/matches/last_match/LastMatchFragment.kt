package com.acdicoding.vianrd.footballfinalsub.view.matches.last_match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.model.Event
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.acdicoding.vianrd.footballfinalsub.view.matches.MatchesView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class LastMatchFragment : Fragment(), MatchesView {

    private var events: MutableList<Event> = mutableListOf()
    private var eventList: MutableList<Event> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter
    private var mPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        rcv_match.layoutManager = LinearLayoutManager(context)
        rcv_match.setHasFixedSize(true)
        adapter = LastMatchAdapter(ctx, events)
        rcv_match.adapter = adapter

        val req = ApiRepository()
        val gson = Gson()

        presenter = LastMatchPresenter(this, req, gson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerId = resources.getIntArray(R.array.id_league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getEventList("${spinnerId[position]}")
            }

        }

        swipeRefresh.onRefresh {
            presenter.getEventList("${spinnerId[mPosition]}")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                events.clear()
                if (p0!!.isNotEmpty()){
                    val search = p0.toLowerCase()
                    eventList.forEach {
                        if (it.titleEvent.toLowerCase().contains(search)){
                            events.add(it)
                        }
                    }
                }else{
                    events.addAll(eventList)
                }
                rcv_match.adapter?.notifyDataSetChanged()
                return true
            }

        })
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showMatchList(data: List<Event>) {
        if (data.isNotEmpty()) {
            swipeRefresh.isRefreshing = false
            events.clear()
            events.addAll(data)
            eventList.addAll(data)
            rcv_match.adapter?.notifyDataSetChanged()
        }
    }

}