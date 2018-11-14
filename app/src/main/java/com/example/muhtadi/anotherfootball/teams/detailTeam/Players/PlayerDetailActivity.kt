package com.example.muhtadi.anotherfootball.teams.detailTeam.Players

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.muhtadi.anotherfootball.R.color.colorAccent
import com.example.muhtadi.anotherfootball.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayerDetailActivity : AppCompatActivity(){

    private lateinit var player: Player
    private lateinit var namePlayer: String
    private lateinit var imagePlayer: String
    private lateinit var descPlayer: String

    private lateinit var playerImage: ImageView
    private lateinit var playerName: TextView
    private lateinit var playerDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        namePlayer = intent.getStringExtra("playerName")
        imagePlayer = intent.getStringExtra("playerImage")
        descPlayer = intent.getStringExtra("playerDesc")

        supportActionBar?.title = namePlayer

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            scrollView {
                isVerticalScrollBarEnabled = false
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    linearLayout{
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(10)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        playerImage =  imageView {}.lparams(height = dip(125))

                        playerName = textView{
                            this.gravity = Gravity.CENTER
                            textSize = 20f
                            textColor = ContextCompat.getColor(context, colorAccent)
                        }.lparams{
                            topMargin = dip(5)
                        }

                        playerDesc = textView().lparams{
                            topMargin = dip(20)
                        }
                    }
                }
            }
        }

        showPlayerDetail()
    }


    fun showPlayerDetail() {
        Picasso.get().load(imagePlayer).into(playerImage)
        playerName.text = namePlayer
        playerDesc.text = descPlayer
    }
}
