package com.acdicoding.vianrd.footballfinalsub.view.teams

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R.layout.item_team
import com.acdicoding.vianrd.footballfinalsub.model.Team
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.DetailTeamActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(val ctx: Context, private val mList: List<Team>):
    RecyclerView.Adapter<TeamAdapter.MatchHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchHolder {
        return MatchHolder(LayoutInflater.from(ctx).inflate(item_team, p0, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: MatchHolder, p1: Int) {
        p0.bindItem(mList[p1])
    }


    class MatchHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bindItem(mTeam: Team){
            itemView.tv_team.text = mTeam.teamName
            Picasso.get().load(mTeam.teamBadge).into(itemView.img_team)
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>("team" to mTeam.teamId)
            }

        }
    }

}