package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.ReposInteractor
import ie.eoinahern.githubclient.ui.repos.ReposUpdatedViewState
import ie.eoinahern.githubclient.ui.repos.ReposView
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ReposPresenter @Inject constructor(
    private val reposInteractor: ReposInteractor,
    private val schedulerProvider: SchedulerProvider
) {

    private val disposables = CompositeDisposable()
    private lateinit var view: ReposView

    fun setView(reposView: ReposView) {
        view = reposView
    }

    fun getRepos() = view.loadRepos()
        .observeOn(schedulerProvider.getIOSchecduler())
        .map { ReposUpdatedViewState.IsProcessing }
        .observeOn(schedulerProvider.getMainSchedulers())
        .subscribe { view.render(it) }


    fun unbind() {
        disposables.clear()
    }

}