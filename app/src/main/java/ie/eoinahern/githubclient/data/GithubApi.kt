package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.model.AccessToken
import ie.eoinahern.githubclient.data.model.RepoItem
import io.reactivex.Observable
import retrofit2.http.*


interface GithubApi {

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    fun getAuthToken(
        @Query("client_id") id: String,
        @Query("client_secret") secret: String,
        @Query("code") code: String,
        @Header("Authorization") authCode: String
    ): Observable<AccessToken>


    @GET("user/repos")
    @Headers("Accept: application/json")
    fun getRepos(@Header("Authorization") authCode: String): Observable<List<RepoItem>>

}