package ie.eoinahern.githubclient.data.repos

import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import io.reactivex.Observable
import javax.inject.Inject


/**
 * a bit of uneccessary abstraction around the api here.
 *
 */

class ReposRepository @Inject constructor(private val api: GithubOauthApi) {

    //fun getReposList(apiKey: String): Observable<List<RepoItem>> = api.getRepos(apiKey, 1, 25)
}