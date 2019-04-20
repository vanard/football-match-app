package com.acdicoding.vianrd.footballfinalsub.view.favorites.fav_match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R.layout.item_match
import com.acdicoding.vianrd.footballfinalsub.db.FavoriteMatch
import com.acdicoding.vianrd.footballfinalsub.utils.DateStringFormat
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_match.DetailMatchActivity
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.startActivity

class FavoriteMatchAdapter(val ctx: Context, private val mList: List<FavoriteMatch>):
    RecyclerView.Adapter<FavoriteMatchAdapter.MatchHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchHolder {
        return MatchHolder(LayoutInflater.from(ctx).inflate(item_match, p0, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: MatchHolder, p1: Int) {
        p0.bindItem(mList[p1])
    }

    class MatchHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bindItem(mFav: FavoriteMatch){
            itemView.tv_tanggal.text = DateStringFormat.reformatStringDate(mFav.dateEvent, DateStringFormat.DATE_FORMAT_YEAR_FIRST, DateStringFormat.DATE_FORMAT_FULL_DATE)
            itemView.tv_hour.text = DateStringFormat.reformatStringDate(mFav.timeEvent, DateStringFormat.DATE_FORMAT_TIME, DateStringFormat.DATE_FORMAT_TIME_LOCAL)
            itemView.tv_home_team.text = mFav.homeTeam
            itemView.tv_away_team.text = mFav.awayTeam
            itemView.score_team_home.text = mFav.homeScore
            itemView.score_team_away.text = mFav.awayScore
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailMatchActivity>("event" to mFav.idEvent)
            }

        }
    }

}