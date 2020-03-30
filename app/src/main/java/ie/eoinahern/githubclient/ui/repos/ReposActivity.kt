package ie.eoinahern.githubclient.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.util.viewmodel.ViewModelCreationFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject


class ReposActivity : AppCompatActivity(), MviView<ReposIntent, ReposViewState> {

    @Inject
    lateinit var factory: ViewModelCreationFactory

    @Inject
    lateinit var adapter: ReposActivityAdapter

    private val disposables = CompositeDisposable()

    private val loadReposPublisher = PublishSubject.create<ReposIntent.LoadReposIntent>()

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

    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    private fun bind() {
        disposables += viewModel.states().subscribe { render(it) }

    }

    override fun intents(): Observable<ReposIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: ReposViewState) {
        //render view!!! yay!!!
    }
}
