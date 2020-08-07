package person.mvvm.practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import person.mvvm.practice.ui.PopularFragment


class MainActivity : AppCompatActivity() {

    companion object {
        const val PARAM_ARTICLE = "param_article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, PopularFragment.newInstance())
            .commit()
    }
}