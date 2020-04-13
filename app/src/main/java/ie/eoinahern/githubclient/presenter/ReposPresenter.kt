package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.ReposInteractor
import ie.eoinahern.githubclient.ui.repos.ReposUpdatedViewState
import ie.eoinahern.githubclient.ui.repos.ReposView
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject


class ReposPresenter @Inject constructor(
    private val reposInteractor: ReposInteractor,
    private val schedulerProvider: SchedulerProvider
) {

    private val disposables = CompositeDisposable()
    private lateinit var view: ReposView

    fun setView(reposView: ReposView) {
        view = reposView
        disposables += getRepos()
    }

    private fun getRepos() = view.loadRepos()
        .observeOn(schedulerProvider.getIOSchecduler())
        .flatMap(reposInteractor::getRepos)
        .onErrorReturn(ReposUpdatedViewState::Error)
        .observeOn(schedulerProvider.getMainSchedulers())
        .startWith(ReposUpdatedViewState.IsProcessing)
        .subscribe { view.render(it) }

    fun unbind() {
        disposables.clear()
    }

}