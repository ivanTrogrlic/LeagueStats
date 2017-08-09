package com.ivantrogrlic.leaguestats.main.summoner.ranks

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.model.LeaguePosition
import com.ivantrogrlic.leaguestats.model.Summoner
import kotlinx.android.synthetic.main.rank_item.view.*
import kotlinx.android.synthetic.main.ranks_page.*
import org.parceler.Parcels

/**
 * Created by ivan on 8/9/2017.
 */

class RanksFragment : MvpFragment<RanksView, RanksPresenter>(), RanksView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.ranks_page, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val summoner = Parcels.unwrap<Summoner>(arguments.getParcelable(HomeFragment.SUMMONER_KEY))
        getPresenter().getLeaguePositions(summoner.id)
        setupLeaguePositionViews()
    }

    override fun createPresenter(): RanksPresenter {
        val application = activity.application as LeagueStatsApplication
        return RanksPresenter(application.component().context(),
                application.netComponent()!!.retrofit())
    }

    override fun showLeaguePositions(leaguePositions: Set<LeaguePosition>) {
        leaguePositions.forEach {
            if (it.isRankedSolo5v5()) setupLeaguesView(it, ranked_solo_card)
            if (it.isRankedFlex5v5()) setupLeaguesView(it, team_flex_card)
            if (it.isRankedTeam3v3()) setupLeaguesView(it, three_vs_three_card)
        }
    }

    private fun setupLeaguesView(it: LeaguePosition, cardView: View) {
        prepareTextViews(cardView)
        cardView.rank_icon.setImageResource(it.getTierIcon())
        cardView.rank.text = getString(R.string.tier_placeholder, it.tier, it.rank)
        cardView.league_points.text = getString(R.string.league_points_placeholder, it.leaguePoints)
        cardView.wins_losses.text = getString(R.string.wins_losses_placeholder, it.wins, it.losses)
        cardView.rank_name.text = it.leagueName

        val winPercentage = calculateWinRatio(it)
        var textColor = ContextCompat.getColor(context, R.color.light_gray)
        if (winPercentage > 50) textColor = ContextCompat.getColor(context, R.color.green)
        if (winPercentage > 60) textColor = ContextCompat.getColor(context, R.color.light_red)

        cardView.win_loss_ratio.setTextColor(textColor)
        cardView.win_loss_ratio.text = getString(R.string.win_ratio_placeholder, winPercentage)
    }

    private fun prepareTextViews(cardView: View) {
        cardView.rank_name.visibility = View.VISIBLE
        cardView.win_ratio_container.visibility = View.VISIBLE
        cardView.wins_losses.visibility = View.VISIBLE
        cardView.league_points.visibility = View.VISIBLE
    }

    private fun setupLeaguePositionViews() {
        ranked_solo_card.queue_type.setText(R.string.ranked_solo)
        team_flex_card.queue_type.setText(R.string.flex_5v5)
        three_vs_three_card.queue_type.setText(R.string.ranked_3v3)
    }

    private fun calculateWinRatio(it: LeaguePosition): Float {
        val totalGames = it.losses.toFloat() + it.wins.toFloat()
        val lossRatio = 1 - (it.losses / totalGames)
        val winPercentage = lossRatio * 100
        return winPercentage
    }

}

