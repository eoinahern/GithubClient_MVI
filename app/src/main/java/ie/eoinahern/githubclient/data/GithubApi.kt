package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.model.AccessToken
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST


interface GithubApi {

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    fun getAuthToken(
        @Field("client_id") id: String,
        @Field("client_secret") secret: String,
        @Field("code") code: String
    ): Observable<AccessToken>

}