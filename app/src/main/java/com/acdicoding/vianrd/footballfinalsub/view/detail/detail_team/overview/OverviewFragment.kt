package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R.layout.fragment_overview
import com.acdicoding.vianrd.footballfinalsub.api.ApiRepository
import com.acdicoding.vianrd.footballfinalsub.model.Team
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment(), OverviewView {

    private lateinit var presenter: OverviewPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val req = ApiRepository()
        val gson = Gson()
        val datas = arguments?.getString("id")

        presenter = OverviewPresenter(this, req, gson)
        presenter.getDataTeam(datas!!)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun setDataTeam(team: Team) {
        tv_description.text = team.teamDescriptionENG
    }

}