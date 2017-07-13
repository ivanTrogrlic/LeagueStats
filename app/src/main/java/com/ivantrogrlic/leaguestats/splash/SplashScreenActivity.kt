package com.ivantrogrlic.leaguestats.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ivantrogrlic.leaguestats.R
import com.ivantrogrlic.leaguestats.main.MainActivity
import kotlinx.android.synthetic.main.splash_screen.*

/**
 * Created by ivanTrogrlic on 12/07/2017.
 */

class SplashScreenActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.splash_screen)
    startAnimation()
  }
  
  private fun startAnimation() {
    val translate = AnimationUtils.loadAnimation(this, R.anim.splash_translate)
    translate.reset()
    text.clearAnimation()
    text.startAnimation(translate)
    
    translate.setAnimationListener(object : Animation.AnimationListener {
      override fun onAnimationEnd(animation: Animation?) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
      }
      
      override fun onAnimationRepeat(animation: Animation?) {}
      override fun onAnimationStart(animation: Animation?) {}
    })
  }
}

