package com.example.muhtadi.anotherfootball.favorites

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.db.FavoriteMatchContract
import com.example.muhtadi.anotherfootball.model.Matches
import org.jetbrains.anko.*

class FavoritesMatchAdapter(private val favoriteMatchContract: List<FavoriteMatchContract>, private val listener: (FavoriteMatchContract) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatchContract[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatchContract.size

}

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(10)
                orientation = LinearLayout.VERTICAL

                textView {
                    id = R.id.match_date
                    textSize = 16f
                }.lparams{
                    margin = dip(4)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(4)
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        id = R.id.home_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(2)
                    }

                    textView {
                        id = R.id.home_score
                        textSize = 16f
                    }.lparams{
                        margin = dip(2)
                    }

                    textView {
                        id = R.id.away_score
                        textSize = 16f
                    }.lparams{
                        margin = dip(2)
                    }

                    textView {
                        id = R.id.away_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(2)
                    }
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
    private val matchDate: TextView = view.find(R.id.match_date)


    fun bindItem(favoriteMatchContract: FavoriteMatchContract, listener: (FavoriteMatchContract) -> Unit) {
        homeName.text = favoriteMatchContract.homeTeam
        homeScore.text = favoriteMatchContract.homeScore
        awayName.text = favoriteMatchContract.awayTeam
        awayScore.text = favoriteMatchContract.awayScore
        matchDate.text = favoriteMatchContract.dateEvent

        itemView.setOnClickListener { listener(favoriteMatchContract) }
    }
}