package com.ivantrogrlic.leaguestats

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash_screen.*


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

