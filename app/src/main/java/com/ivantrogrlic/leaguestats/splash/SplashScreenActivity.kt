package com.ivantrogrlic.leaguestats.splash

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.MainActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.splash_screen.*
import javax.inject.Inject

/**
 * Created by ivanTrogrlic on 12/07/2017.
 */

class SplashScreenActivity : SplashView, MvpActivity<SplashView, SplashPresenter>(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var splashPresenter: SplashPresenter

    override fun activityInjector() = activityInjector

    override fun createPresenter() = splashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        getPresenter().handleServerSelection()
    }

    override fun animateLogo() {
        val translate = AnimationUtils.loadAnimation(this, R.anim.splash_translate)
        translate.reset()
        text.clearAnimation()
        text.startAnimation(translate)

        translate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                getPresenter().logoAnimationFinished()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
        })
    }

    override fun showServerChoiceDialog(choiceArray: Array<CharSequence>) {
        AlertDialog.Builder(this)
                .setCustomTitle(View.inflate(this, R.layout.server_select_dialog_title, null))
                .setSingleChoiceItems(choiceArray, 0, { dialog, which ->
                    dialog.dismiss()
                    getPresenter().serverSelected(which)
                })
                .setOnCancelListener { getPresenter().serverSelected(0) }
                .create()
                .show()
    }

    override fun goToMainScreen() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

}
