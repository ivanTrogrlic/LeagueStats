package com.ivantrogrlic.leaguestats.main.summoner.games

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.model.Match
import com.ivantrogrlic.leaguestats.model.Summoner
import kotlinx.android.synthetic.main.games_fragment.*
import org.parceler.Parcels


/**
 * Created by ivan on 8/9/2017.
 */

class GamesFragment : GamesView, MvpFragment<GamesView, GamesPresenter>() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gamesAdapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.games_fragment, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        gamesAdapter = GamesAdapter(context)
        recyclerView.adapter = gamesAdapter

        val summoner = Parcels.unwrap<Summoner>(arguments.getParcelable(HomeFragment.SUMMONER_KEY))
        presenter.getRecentMatches(summoner)
    }

    override fun createPresenter(): GamesPresenter {
        val application = activity.application as LeagueStatsApplication
        return GamesPresenter(context, application.netComponent()!!.riotWebService())
    }

    override fun setMatches(matches: List<Match>) {
        gamesAdapter.setMatches(matches)
    }

}
