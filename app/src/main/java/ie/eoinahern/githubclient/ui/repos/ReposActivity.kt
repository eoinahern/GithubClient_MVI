package ie.eoinahern.githubclient.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.eoinahern.githubclient.R
import kotlinx.android.synthetic.main.activity_repos.*


class ReposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}
