package com.ivantrogrlic.leaguestats.main.summoner.games

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.home.HomeFragment
import com.ivantrogrlic.leaguestats.model.Match
import com.ivantrogrlic.leaguestats.model.Summoner
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.games_fragment.*
import org.parceler.Parcels
import javax.inject.Inject


/**
 * Created by ivan on 8/9/2017.
 */

class GamesFragment : GamesView, MvpFragment<GamesView, GamesPresenter>(), HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var gamesPresenter: GamesPresenter

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gamesAdapter: GamesAdapter

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    override fun createPresenter() = gamesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.games_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        gamesAdapter = GamesAdapter(context!!)
        recyclerView.adapter = gamesAdapter

        val summoner = Parcels.unwrap<Summoner>(arguments!!.getParcelable(HomeFragment.SUMMONER_KEY))
        presenter.getRecentMatches(summoner)
    }

    override fun setMatches(matches: List<Match>) {
        gamesAdapter.setMatches(matches)
    }

}
