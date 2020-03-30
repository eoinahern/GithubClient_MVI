package ie.eoinahern.githubclient.data.repos

import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.data.model.RepoItem
import io.reactivex.Observable
import javax.inject.Inject


class ReposRepository @Inject constructor(private val api: GithubApi) {

    fun getReposList(apiKey: String): Observable<List<RepoItem>> = api.getRepos(apiKey)
}