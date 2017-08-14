package com.ivantrogrlic.leaguestats.main.summoner.games

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.model.Match
import com.ivantrogrlic.leaguestats.model.queueName
import com.ivantrogrlic.leaguestats.web.getItemIconUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_item.view.*
import java.util.*

/**
 * Created by ivan on 8/11/2017.
 */
class GamesAdapter(val context: Context) : RecyclerView.Adapter<GamesAdapter.GameHolder>() {

    private var matches: ArrayList<Match>? = null

    fun setMatches(matches: ArrayList<Match>) {
        this.matches = matches
        notifyDataSetChanged()
    }

    class GameHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var matchType: TextView = v.match_type
        private var matchTime: TextView = v.match_time
        private var matchLength: TextView = v.match_length
        private var championIcon: ImageView = v.champion_icon
        private var summonerSpell1: ImageView = v.summoner_spell_1
        private var summonerSpell2: ImageView = v.summoner_spell_2
        private var score: TextView = v.score
        private var kda: TextView = v.kda
        private var cs: TextView = v.cs
        private var item1: ImageView = v.item_1
        private var item2: ImageView = v.item_2
        private var item3: ImageView = v.item_3
        private var item4: ImageView = v.item_4
        private var item5: ImageView = v.item_5
        private var item6: ImageView = v.item_6
        private var trinket: ImageView = v.trinket

        fun bindMatch(context: Context, match: Match) {
            val participant = match.participants.first()
            val stats = participant.stats
            matchType.text = queueName(context, match.queue)
            matchTime.text = match.gameCreation.toString()
            matchLength.text = match.gameDuration.toString()
            matchLength.text = match.gameDuration.toString()
            Picasso.with(context).load(getItemIconUrl(stats.item0)).into(item1)
            Picasso.with(context).load(getItemIconUrl(stats.item1)).into(item2)
            Picasso.with(context).load(getItemIconUrl(stats.item2)).into(item3)
            Picasso.with(context).load(getItemIconUrl(stats.item3)).into(item4)
            Picasso.with(context).load(getItemIconUrl(stats.item4)).into(item5)
            Picasso.with(context).load(getItemIconUrl(stats.item5)).into(item6)
            Picasso.with(context).load(getItemIconUrl(stats.item6)).into(trinket)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val inflatedView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.game_item, parent, false)
        return GameHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        matches?.let { holder.bindMatch(context, it[position]) }
    }

    override fun getItemCount(): Int {
        return matches?.size ?: 0
    }
}
