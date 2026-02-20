package com.cargram.app.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cargram.app.MainActivity
import com.cargram.app.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animateSplash()
    }

    private fun animateSplash() {
        val ivLogo = findViewById<ImageView>(R.id.iv_logo)
        val tvAppName = findViewById<TextView>(R.id.tv_app_name)
        val tvTagline = findViewById<TextView>(R.id.tv_tagline)

        // Fade + scale in logo
        val logoAlpha = ObjectAnimator.ofFloat(ivLogo, "alpha", 0f, 1f).apply {
            duration = 600
            startDelay = 100
        }
        val logoScaleX = ObjectAnimator.ofFloat(ivLogo, "scaleX", 0.6f, 1f).apply {
            duration = 600
            startDelay = 100
            interpolator = DecelerateInterpolator()
        }
        val logoScaleY = ObjectAnimator.ofFloat(ivLogo, "scaleY", 0.6f, 1f).apply {
            duration = 600
            startDelay = 100
            interpolator = DecelerateInterpolator()
        }

        // Fade in text
        val nameAlpha = ObjectAnimator.ofFloat(tvAppName, "alpha", 0f, 1f).apply {
            duration = 500
            startDelay = 400
        }
        val nameTranslate = ObjectAnimator.ofFloat(tvAppName, "translationY", 20f, 0f).apply {
            duration = 500
            startDelay = 400
        }
        val taglineAlpha = ObjectAnimator.ofFloat(tvTagline, "alpha", 0f, 1f).apply {
            duration = 500
            startDelay = 600
        }

        AnimatorSet().apply {
            playTogether(logoAlpha, logoScaleX, logoScaleY, nameAlpha, nameTranslate, taglineAlpha)
            start()
        }

        // Navigate after delay
        Handler(Looper.getMainLooper()).postDelayed({
            navigateNext()
        }, 2200)
    }

    private fun navigateNext() {
        // For demo: always go to Login
        // In production: check saved session
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
