package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.model.RepoItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface GithubOauthApi {

    @GET("user/repos")
    @Headers("Accept: application/json")
    fun getRepos(@Header("Authorization") authCode: String): Observable<List<RepoItem>>
}