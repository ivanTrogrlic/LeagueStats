package com.ivantrogrlic.leaguestats.splash

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.ivantrogrlic.leaguestats.dagger.ApplicationContext
import com.ivantrogrlic.leaguestats.dagger.PerActivity
import com.ivantrogrlic.leaguestats.model.ServiceProxy
import com.ivantrogrlic.leaguestats.model.ServiceProxy.values
import com.ivantrogrlic.leaguestats.util.Preferences
import javax.inject.Inject


/**
 * Created by ivanTrogrlic on 16/07/2017.
 */

@PerActivity
class SplashPresenter @Inject constructor(@ApplicationContext private val context: Context,
                                          private val sharedPreferences: SharedPreferences)
    : MvpBasePresenter<SplashView>() {

    private var serverChoice: Array<CharSequence> = values().map { it.serverName(context) }.toTypedArray()

    fun handleServerSelection() {
        val selectedServer = sharedPreferences.getString(Preferences.SERVER_PROXY_KEY,
                Preferences.NO_SERVER_SELECTED)
        if (Preferences.NO_SERVER_SELECTED == selectedServer) view.animateLogo()
        else {
            view.goToMainScreen()
        }
    }

    fun logoAnimationFinished() = view.showServerChoiceDialog(serverChoice)

    fun serverSelected(selectedServerPosition: Int) {
        val serviceProxy = ServiceProxy.values()[selectedServerPosition]

        saveProxyToPrefs(serviceProxy)

        val mStartActivity = Intent(context, SplashScreenActivity::class.java)
        val mPendingIntentId = 123456
        val mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
        System.exit(0)
    }

    private fun saveProxyToPrefs(serviceProxy: ServiceProxy) =
            sharedPreferences.edit()
                    .putString(Preferences.SERVER_PROXY_KEY, serviceProxy.name)
                    .commit()

}
