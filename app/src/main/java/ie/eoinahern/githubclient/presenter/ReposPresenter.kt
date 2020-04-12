package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.ReposInteractor
import ie.eoinahern.githubclient.ui.repos.ReposView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ReposPresenter @Inject constructor(private val reposInteractor: ReposInteractor) {

    private val disposables = CompositeDisposable()
    private lateinit var view: ReposView


    fun setView(reposView: ReposView) {
        view = reposView
    }


    fun getRepos() {

    }

    fun unbind() {
        disposables.clear()
    }

}