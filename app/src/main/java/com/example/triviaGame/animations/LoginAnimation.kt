package com.example.triviaGame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R
import com.example.triviaGame.HomepageActivity
import com.example.triviaGame.MainActivity
import kotlinx.android.synthetic.main.activity_login_animation.*

class LoginAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_animation)
        welcomeBack.alpha = 0f
        welcomeBack.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
            finish()
        }
    }
}