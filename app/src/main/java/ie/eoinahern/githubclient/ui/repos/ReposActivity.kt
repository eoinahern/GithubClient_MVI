package ie.eoinahern.githubclient.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.util.viewmodel.ViewModelCreationFactory
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject


class ReposActivity : AppCompatActivity() {


    @Inject
    lateinit var factory: ViewModelCreationFactory

    private lateinit var viewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        setUpToolbar()

        (application as GithubApp).getAppComponent().inject(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ReposViewModel::class.java)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }
}
