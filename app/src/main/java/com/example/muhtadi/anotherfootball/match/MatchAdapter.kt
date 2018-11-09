package com.example.muhtadi.anotherfootball.match

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MatchAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.team_year
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.team_stadium
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)
    private val teamYear: TextView = view.find(R.id.team_year)
    private val teamStaduim: TextView = view.find(R.id.team_stadium)

    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        Picasso.get().load(teams.strTeamBadge).into(teamBadge)
        teamName.text = teams.strTeam
        teamYear.text = teams.intFormedYear
        teamStaduim.text = teams.strStadium
        itemView.setOnClickListener { listener(teams) }
    }
}