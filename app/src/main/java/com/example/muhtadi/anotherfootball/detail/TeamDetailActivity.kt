package com.example.muhtadi.anotherfootball.detail

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
import com.example.muhtadi.anotherfootball.R.color.colorAccent
import com.example.muhtadi.anotherfootball.R.id.add_to_favorites
import com.example.muhtadi.anotherfootball.R.menu.favorites_menu
import com.example.muhtadi.anotherfootball.api.ApiRepository
import com.example.muhtadi.anotherfootball.db.FavoriteTeamContract
import com.example.muhtadi.anotherfootball.db.database
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

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
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

                            teamBadge =  imageView {}.lparams(height = dip(75))

                            teamName = textView{
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, colorAccent)
                            }.lparams{
                                topMargin = dip(5)
                            }

                            teamFormedYear = textView{
                                this.gravity = Gravity.CENTER
                            }

                            teamStadium = textView{
                                this.gravity = Gravity.CENTER

                            }

                            teamDescription = textView().lparams{
                                topMargin = dip(20)
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
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeamContract.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeamContract>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].idTeam,
                data[0].strTeam,
                data[0].strTeamBadge)
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].strTeamBadge).into(teamBadge)
        teamName.text = data[0].strTeam
        teamDescription.text = data[0].strDescriptionEN
        teamFormedYear.text = data[0].intFormedYear
        teamStadium.text = data[0].strStadium

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favorites_menu, menu)
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
            add_to_favorites -> {
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
                insert(FavoriteTeamContract.TABLE_FAVORITE,
                        FavoriteTeamContract.TEAM_ID to teams.idTeam,
                        FavoriteTeamContract.TEAM_NAME to teams.strTeam,
                        FavoriteTeamContract.TEAM_BADGE to teams.strTeamBadge)
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
                delete(FavoriteTeamContract.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
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