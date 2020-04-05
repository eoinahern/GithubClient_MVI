package ie.eoinahern.githubclient.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.util.viewmodel.ViewModelCreationFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject


class ReposActivity : AppCompatActivity(), MviView<ReposIntent, ReposViewState> {

    @Inject
    lateinit var factory: ViewModelCreationFactory

    @Inject
    lateinit var adapter: ReposActivityAdapter

    private val disposables = CompositeDisposable()

    private val loadReposPublisher = BehaviorSubject.create<ReposIntent.LoadReposIntent>()

    private lateinit var viewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        setUpToolbar()

        (application as GithubApp).getAppComponent().inject(this)
        setUpAdapter()
        setupViewModel()

        val key = intent.getStringExtra("key")
        loadReposPublisher.onNext(ReposIntent.LoadReposIntent("token ".plus(key) ?: ""))
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ReposViewModel::class.java)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun setUpAdapter() {
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun updateAdapter(list: List<RepoItem>) {
        adapter.updateAdapter(list)
    }

    private fun getLoadReposObservable(): Observable<ReposIntent.LoadReposIntent> =
        loadReposPublisher

    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun bind() {
        disposables += viewModel.states().subscribe { render(it) }
        viewModel.processIntents(intents())
    }

    override fun intents(): Observable<ReposIntent> {
        return getLoadReposObservable().cast(ReposIntent::class.java)
    }

    override fun render(state: ReposViewState) {

        progressbar.isVisible = state.isProcessing && !recycler.isVisible

        if (!state.data.isNullOrEmpty()) {
            updateAdapter(state.data)
            recycler.isVisible = true
        }
    }
}
