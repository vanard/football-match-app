package com.acdicoding.vianrd.footballfinalsub.view.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.array.league
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.model.Team
import com.acdicoding.vianrd.footballfinalsub.utils.invisible
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class TeamsFragment : Fragment(), TeamView {

    private var teams: MutableList<Team> = mutableListOf()
    private var teamList: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamAdapter
    private var mPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        rcv_team.layoutManager = LinearLayoutManager(context)
        rcv_team.setHasFixedSize(true)
        adapter = TeamAdapter(ctx, teams)
        rcv_team.adapter = adapter

        val req = ApiRepository()
        val gson = Gson()

        presenter = TeamsPresenter(this, req, gson)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mPosition = position
                presenter.getEventList(spinnerItems[position])
            }

        }

        swipeRefresh.onRefresh {
            presenter.getEventList(spinnerItems[mPosition])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Search teams"

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                teams.clear()
                if (p0!!.isNotEmpty()){
                    val search = p0.toLowerCase()
                    teamList.forEach {
                        if (it.teamName!!.toLowerCase().contains(search)){
                            teams.add(it)
                        }
                    }
                }else{
                    teams.addAll(teamList)
                }
                rcv_team.adapter?.notifyDataSetChanged()
                return true
            }

        })
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        if (data.isNotEmpty()) {
            swipeRefresh.isRefreshing = false
            teams.clear()
            teams.addAll(data)
            teamList.addAll(data)
            rcv_team.adapter?.notifyDataSetChanged()
        }
    }

}