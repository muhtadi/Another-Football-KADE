package com.example.muhtadi.anotherfootball.match

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.model.Matches
import org.jetbrains.anko.*

class MatchAdapter(private val matches: List<Matches>, private val listener: (Matches) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    override fun getItemCount(): Int = matches.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                textView {
                    id = R.id.home_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.home_score
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.away_score
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.away_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val homeName: TextView = view.find(R.id.home_name)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayName: TextView = view.find(R.id.away_name)
    private val awayScore: TextView = view.find(R.id.away_score)
    //private val matchDate: TextView = view.find(R.id.match_date)


    fun bindItem(matches: Matches, listener: (Matches) -> Unit) {
        homeName.text = matches.strHomeTeam
        homeScore.text = matches.intHomeScore
        awayName.text = matches.strAwayTeam
        awayScore.text = matches.intAwayScore
        //matchDate.text = matches.str

        itemView.setOnClickListener { listener(matches) }
    }
}