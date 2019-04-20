package com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acdicoding.vianrd.footballfinalsub.R
import com.acdicoding.vianrd.footballfinalsub.R.layout.item_player
import com.acdicoding.vianrd.footballfinalsub.model.Player
import com.acdicoding.vianrd.footballfinalsub.view.detail.detail_team.players.detail_player.DetailPlayerActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(val ctx: Context, private val mList: List<Player>):
    RecyclerView.Adapter<PlayerAdapter.PlayerHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerHolder {
        return PlayerHolder(LayoutInflater.from(ctx).inflate(item_player, p0, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: PlayerHolder, p1: Int) {
        p0.bindItem(mList[p1])
    }

    class PlayerHolder (v: View): RecyclerView.ViewHolder(v) {
        fun bindItem(player: Player){
            if (player.playerPic.isNullOrEmpty()){
                itemView.img_player.setImageResource(R.drawable.avatar)
            }else{
                Picasso.get().load(player.playerPic).into(itemView.img_player)
            }
            itemView.tv_player.text = player.playerName
            itemView.tv_lineup.text = player.playerPost
            itemView.setOnClickListener { itemView.context.startActivity<DetailPlayerActivity>("id" to player.playerId) }
        }
    }

}