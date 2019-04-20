package com.acdicoding.vianrd.footballfinalsub.view.favorites.fav_team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R.layout.item_team
import com.acdicoding.vianrd.footballfinalsub.db.FavoriteTeam
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.DetailTeamActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class FavoriteTeamAdapter(val ctx: Context, private val mList: List<FavoriteTeam>):
    RecyclerView.Adapter<FavoriteTeamAdapter.TeamHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamHolder {
        return TeamHolder(LayoutInflater.from(ctx).inflate(item_team, p0, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: TeamHolder, p1: Int) {
        p0.bindItem(mList[p1])
    }

    class TeamHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bindItem(mFav: FavoriteTeam){
            itemView.tv_team.text = mFav.teamName
            Picasso.get().load(mFav.teamBadge).into(itemView.img_team)
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>("team" to mFav.idTeam)
            }

        }
    }

}