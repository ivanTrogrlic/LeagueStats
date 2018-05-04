package com.ivantrogrlic.leaguestats.main.home

import android.app.Activity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.summoner.SummonerActivity
import com.ivantrogrlic.leaguestats.model.Summoner
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import org.parceler.Parcels
import javax.inject.Inject

/**
 * Created by ivanTrogrlic on 14/07/2017.
 */

class HomeFragment : MvpFragment<HomeView, HomePresenter>(), HomeView, HasSupportFragmentInjector {

    companion object {
        const val SUMMONER_KEY = "SUMMONER_KEY"
    }

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var homePresenter: HomePresenter

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    override fun createPresenter() = homePresenter

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
            inflater.inflate(R.layout.home_fragment, container, false)

    override fun onResume() {
        super.onResume()
        search.setOnClickListener {
            startSearchAnimation(R.drawable.search_animation)
            getPresenter().searchForSummoner(summoner_input.text.toString())
        }
    }

    override fun setHint(text: String) {
        hint.text = text
    }

    override fun summonerLoaded(summoner: Summoner) {
        startActivity<SummonerActivity>(SUMMONER_KEY to Parcels.wrap(Summoner::class.java, summoner))

        context?.let {
            val create = AnimatedVectorDrawableCompat.create(it, R.drawable.search_animation)
            search.setImageDrawable(create)
        }
    }

    override fun searchingFailed() {
        context?.let {
            startSearchAnimation(R.drawable.search_animation_stop)
            summoner_input.error = it.getString(R.string.summoner_search_failed_general)
        }
    }

    override fun summonerNotFound() {
        context?.let {
            startSearchAnimation(R.drawable.search_animation_stop)
            summoner_input.error = it.getString(R.string.summoner_search_failed_not_found)
        }
    }

    private fun startSearchAnimation(@DrawableRes animationId: Int) {
        context?.let {
            val create = AnimatedVectorDrawableCompat.create(it, animationId)
            search.setImageDrawable(create)
            create?.start()
        }
    }

}
