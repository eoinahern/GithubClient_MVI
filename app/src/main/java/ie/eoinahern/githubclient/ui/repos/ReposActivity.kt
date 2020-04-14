package ie.eoinahern.githubclient.ui.repos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.presenter.ReposPresenter
import ie.eoinahern.githubclient.util.viewmodel.ViewModelCreationFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject


class ReposActivity : AppCompatActivity(), ReposView {

    @Inject
    lateinit var adapter: ReposActivityAdapter

    private val loadReposPublisher = BehaviorSubject.create<String>()

    @Inject
    lateinit var presenter: ReposPresenter

    private var key: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        setUpToolbar()
        (application as GithubApp).getAppComponent().inject(this)
        setUpAdapter()
        getApiKey()
        presenter.setView(this)
        sendLoadReposRequest()
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

    private fun getApiKey() {
        key = intent.getStringExtra("key") ?: ""
    }

    private fun sendLoadReposRequest() {
        loadReposPublisher.onNext("token ".plus(key))
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }

    private fun updateAdapter(list: LiveData<PagedList<RepoItem>>) {
        list.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun render(viewState: ReposUpdatedViewState) {
        when (viewState) {
            is ReposUpdatedViewState.Error -> {
                println(viewState.error?.message)
            }

            is ReposUpdatedViewState.Complete -> {
                progressbar.isVisible = false
                showGitRepos(viewState.data)
            }
            is ReposUpdatedViewState.IsProcessing -> {
                progressbar.isVisible = !recycler.isVisible
            }
        }
    }

    private fun showGitRepos(list: PagedList<RepoItem>) {
        adapter.submitList(list)
        recycler.isVisible = true
    }

    override fun loadRepos(): Observable<String> {
        return loadReposPublisher
    }
}
