package com.acdicoding.vianrd.footballfinalsub.view.favorites.fav_match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.layout.fragment_fav
import com.acdicoding.vianrd.footballfinalsub.db.FavoriteMatch
import com.acdicoding.vianrd.footballfinalsub.db.dab
import com.acdicoding.vianrd.footballfinalsub.utils.gone
import com.acdicoding.vianrd.footballfinalsub.utils.visible
import kotlinx.android.synthetic.main.fragment_fav.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteMatchFragment : Fragment() {

    private var fav: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rcv_fav.layoutManager = LinearLayoutManager(ctx)
        rcv_fav.setHasFixedSize(true)
        adapter = FavoriteMatchAdapter(ctx, fav)
        rcv_fav.adapter = adapter

        tv_no_fav.text = getString(R.string.no_fav_match)
        showFav()

        swipeRefresh.onRefresh {
            fav.clear()
            showFav()
        }

    }

    private fun showFav(){
        context?.dab?.use {
            swipeRefresh.isRefreshing = false
            val res = select(FavoriteMatch.TABLE_FAV_MATCH)
            val favv = res.parseList(classParser<FavoriteMatch>())
            fav.addAll(favv)
            rcv_fav.adapter?.notifyDataSetChanged()

            if (fav.size < 1){
                tv_no_fav.visible()
            }else{
                tv_no_fav.gone()
            }
        }
    }

}