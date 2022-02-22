import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviaGame.R

class TrueFalse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_true_false)
        supportActionBar?.hide()
    }
}