package com.example.muhtadi.anotherfootball.teams.detailTeam.Players

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayersAdapter(private val player: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(player[position], listener)
    }

    override fun getItemCount(): Int = player.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_image
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.player_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerImage: ImageView = view.find(R.id.player_image)
    private val playerName: TextView = view.find(R.id.player_name)

    fun bindItem(player: Player, listener: (Player) -> Unit) {
        Picasso.get().load(player.strCutout).into(playerImage)
        playerName.text = player.strPlayer
        itemView.setOnClickListener { listener(player) }
    }
}