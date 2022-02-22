package com.example.triviaGame.animations
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R
import com.example.triviaGame.HomepageActivity
import kotlinx.android.synthetic.main.activity_login_animation.*
import kotlinx.android.synthetic.main.activity_main_screen_animation.*
import kotlinx.android.synthetic.main.activity_startup_animation.*

class MainScreenAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen_animation)
        triviaSelect.alpha = 0f
        triviaSelect.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this, HomepageActivity::class.java)

            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out)
            finish()
        }
    }
}