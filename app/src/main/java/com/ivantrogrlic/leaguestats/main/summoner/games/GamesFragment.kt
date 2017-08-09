package com.ivantrogrlic.leaguestats.main.summoner.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.R

/**
 * Created by ivan on 8/9/2017.
 */

class GamesFragment : GamesView, MvpFragment<GamesView, GamesPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.home_fragment, container, false)

    override fun createPresenter(): GamesPresenter {
        return GamesPresenter()
    }

    override fun test() {

    }

}
