package com.ivantrogrlic.leaguestats.splash

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

interface SplashView : MvpView {
  fun showServerChoiceDialog(choiceArray: Array<CharSequence>)
  fun animateLogo()
  fun goToMainScreen()
}
