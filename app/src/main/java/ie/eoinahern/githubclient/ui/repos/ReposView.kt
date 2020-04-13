package ie.eoinahern.githubclient.ui.repos

import io.reactivex.Observable

interface ReposView {
    fun render(viewState: ReposUpdatedViewState)
    fun loadRepos(): Observable<String>
}