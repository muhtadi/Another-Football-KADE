package com.example.muhtadi.anotherfootball.match.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.muhtadi.anotherfootball.R
import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.db.FavoriteMatchContract
import com.example.muhtadi.anotherfootball.db.database
import com.example.muhtadi.anotherfootball.model.Matches
import com.example.muhtadi.anotherfootball.model.Team
import com.example.muhtadi.anotherfootball.util.invisible
import com.example.muhtadi.anotherfootball.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var matches: Matches
    private lateinit var teams: Team
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var dateEvents: TextView
    private lateinit var homeBadge: ImageView
    private lateinit var homeTeam: TextView
    private lateinit var homeScore: TextView
    private lateinit var homeGoaler: TextView
    private lateinit var awayBadge: ImageView
    private lateinit var awayTeam: TextView
    private lateinit var awayScore: TextView
    private lateinit var awayGoaler: TextView


    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var matchId: String
    private lateinit var homeId: String
    private lateinit var awayId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = intent
        matchId = intent.getStringExtra("matchId")
        homeId = intent.getStringExtra("homeTeamId")
        awayId = intent.getStringExtra("awayTeamId")

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        linearLayout{
                            lparams(width = matchParent, height = wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            dateEvents = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 18f
                            }.lparams{
                                topMargin = dip(5)
                                bottomMargin = dip(5)
                            }

                            homeBadge = imageView {  }.lparams(height=dip(75))

                            homeTeam = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 18f
                            }.lparams{
                                topMargin = dip(3)
                                bottomMargin = dip(3)
                            }

                            homeScore = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 25f
                            }.lparams{
                                topMargin = dip(3)
                                bottomMargin = dip(3)
                            }

                            homeGoaler = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 15f
                            }.lparams{
                                topMargin = dip(3)
                                bottomMargin = dip(15)
                            }

                            awayBadge= imageView {  }.lparams(height=dip(75))

                            awayTeam= textView {
                                this.gravity = Gravity.CENTER
                                textSize = 18f
                            }.lparams{
                                topMargin = dip(3)
                                bottomMargin = dip(3)
                            }

                            awayScore = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 25f
                            }.lparams{
                                topMargin = dip(3)
                                bottomMargin = dip(3)
                            }

                            awayGoaler = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 15f
                            }.lparams{
                                topMargin = dip(3)
                                bottomMargin = dip(3)
                            }

                        }
                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }
        }

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)
        presenter.getMatchDetail(matchId)
        presenter.getHomeBadge(homeId)
        presenter.getAwayBadge(awayId)

        swipeRefresh.onRefresh {
            presenter.getMatchDetail(matchId)
            presenter.getHomeBadge(homeId)
            presenter.getAwayBadge(awayId)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatchContract.TABLE_MATCH_FAVORITE)
                    .whereArgs("(MATCH_ID = {id})",
                            "id" to matchId)
            val favorite = result.parseList(classParser<FavoriteMatchContract>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchDetail(data: List<Matches>) {
        matches = Matches(data[0].idEvent,
                data[0].dateEvent,
                data[0].idAwayTeam,
                data[0].strAwayTeam,
                data[0].intAwayScore,
                data[0].strAwayGoalDetails,
                data[0].idHomeTeam,
                data[0].strHomeTeam,
                data[0].intHomeScore,
                data[0].strHomeGoalDetails)
        swipeRefresh.isRefreshing = false

        dateEvents.text = data[0].dateEvent
        homeTeam.text = data[0].strHomeTeam
        homeScore.text = data[0].intHomeScore
        homeGoaler.text = data[0].strHomeGoalDetails

        awayTeam.text = data[0].strAwayTeam
        awayScore.text = data[0].intAwayScore
        awayGoaler.text = data[0].strAwayGoalDetails
    }

    override fun showHomeBadge(data: List<Team>) {
        teams = Team(data[0].idTeam,
                data[0].strTeamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].strTeamBadge).into(homeBadge)
    }

    override fun showAwayBadge(data: List<Team>) {
        teams = Team(data[0].idTeam,
                data[0].strTeamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].strTeamBadge).into(awayBadge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorites -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteMatchContract.TABLE_MATCH_FAVORITE,
                        FavoriteMatchContract.MATCH_ID to matches.idEvent,
                        FavoriteMatchContract.DATE_EVENT to matches.dateEvent,
                        FavoriteMatchContract.HOME_ID to matches.idHomeTeam,
                        FavoriteMatchContract.HOME_TEAM to matches.strHomeTeam,
                        FavoriteMatchContract.HOME_SCORE to matches.intHomeScore,
                        FavoriteMatchContract.AWAY_ID to matches.idAwayTeam,
                        FavoriteMatchContract.AWAY_TEAM to matches.strAwayTeam,
                        FavoriteMatchContract.AWAY_SCORE to matches.intAwayScore)
            }
            //swipeRefresh.snackbar("Added to favorite").show()
            toast("Added to favorite")

        } catch (e: SQLiteConstraintException){
            //swipeRefresh.snackbar(e.localizedMessage).show()
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatchContract.TABLE_MATCH_FAVORITE, "(MATCH_ID = {id})",
                        "id" to matchId)
            }
            //swipeRefresh.snackbar( "Removed to favorite").show()
            toast("Removed to favorite")
        } catch (e: SQLiteConstraintException){
            //swipeRefresh.snackbar(e.localizedMessage).show()
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
    }
}