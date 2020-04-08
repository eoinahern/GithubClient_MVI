package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.model.RepoItem
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface GithubOauthApi {

    @GET("user/repos")
    @Headers("Accept: application/json")
    fun getRepos(
        @Header("Authorization") authCode: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<RepoItem>>
}