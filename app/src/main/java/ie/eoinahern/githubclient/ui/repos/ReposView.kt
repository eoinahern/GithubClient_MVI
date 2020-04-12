package ie.eoinahern.githubclient.ui.repos

import io.reactivex.Observable

interface ReposView {
    fun render()
    fun loadRepos(): Observable<String>
}