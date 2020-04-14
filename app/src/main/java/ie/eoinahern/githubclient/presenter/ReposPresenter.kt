package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.ReposInteractor
import ie.eoinahern.githubclient.ui.repos.ReposUpdatedViewState
import ie.eoinahern.githubclient.ui.repos.ReposView
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class ReposPresenter @Inject constructor(
    private val reposInteractor: ReposInteractor
) {

    private val disposables = CompositeDisposable()
    private lateinit var view: ReposView

    fun setView(reposView: ReposView) {
        view = reposView
        disposables += getRepos()
    }

    private fun getRepos() = view.loadRepos()
        .flatMap(reposInteractor::getRepos)
        .map(ReposUpdatedViewState::Complete)
        .subscribeBy(
            onNext = { state ->
                view.render(state)
            }
            , onError = { throwable ->
                view.render(ReposUpdatedViewState.Error(throwable))
            })


    fun unbind() {
        disposables.clear()
    }
}