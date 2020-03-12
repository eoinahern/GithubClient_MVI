package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.model.AccessToken
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface GithubApi {

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    fun getAuthToken(
        @Query("client_id") id: String,
        @Query("client_secret") secret: String,
        @Query("code") code: String
    ): Observable<AccessToken>

}