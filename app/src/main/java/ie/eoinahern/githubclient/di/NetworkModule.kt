package ie.eoinahern.githubclient.di

import dagger.Module
import dagger.Provides
import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.util.constants.GITHUBAPI_URL
import ie.eoinahern.githubclient.util.constants.GITHUB_OAUTH_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(GITHUBAPI_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GithubApi::class.java)
    }


    @Singleton
    @Provides
    fun getOauhtApi(): GithubOauthApi {
        return Retrofit.Builder()
            .baseUrl(GITHUB_OAUTH_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GithubOauthApi::class.java)
    }
}