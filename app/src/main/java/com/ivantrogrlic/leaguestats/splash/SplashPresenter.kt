package com.ivantrogrlic.leaguestats.splash

import android.content.Context
import android.content.SharedPreferences
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.LeagueStatsApplication
import com.ivantrogrlic.leaguestats.model.ServiceProxy
import com.ivantrogrlic.leaguestats.model.ServiceProxy.valueOf
import com.ivantrogrlic.leaguestats.model.ServiceProxy.values
import com.ivantrogrlic.leaguestats.util.Preferences

/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

class SplashPresenter(val context: Context, val sharedPreferences: SharedPreferences)
  : MvpBasePresenter<SplashView>() {
  
  private var serverChoice: Array<CharSequence> = values().map { it.serverName(context) }.toTypedArray()
  
  fun handleServerSelection() {
    val selectedServer = sharedPreferences.getString(Preferences.SERVER_PROXY_KEY,
                                                     Preferences.NO_SERVER_SELECTED)
    if (Preferences.NO_SERVER_SELECTED == selectedServer) view.animateLogo()
    else {
      setupNetComponent(valueOf(selectedServer))
      view.goToMainScreen()
    }
  }
  
  fun logoAnimationFinished() {
    view.showServerChoiceDialog(serverChoice)
  }
  
  fun serverSelected(selectedServerPosition: Int) {
    val serviceProxy = ServiceProxy.values()[selectedServerPosition]
    
    saveProxyToPrefs(serviceProxy)
    setupNetComponent(serviceProxy)
    
    view.goToMainScreen()
  }
  
  private fun saveProxyToPrefs(serviceProxy: ServiceProxy) {
    sharedPreferences.edit()
        .putString(Preferences.SERVER_PROXY_KEY, serviceProxy.name)
        .apply()
  }
  
  private fun setupNetComponent(serviceProxy: ServiceProxy) {
    val leagueStatsApplication = context.applicationContext as LeagueStatsApplication
    leagueStatsApplication.destroyNetComponent()
    leagueStatsApplication.createNetComponent(serviceProxy.serverHost())
  }
}
