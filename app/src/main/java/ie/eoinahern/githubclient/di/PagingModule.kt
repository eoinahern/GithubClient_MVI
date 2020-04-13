package ie.eoinahern.githubclient.di


import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.ReposDataSourceFactory
import javax.inject.Singleton


@Module
class PagingModule {

    @Singleton
    @Provides
    fun getConfig(): PagedList.Config.Builder =
        PagedList.Config.Builder().setPageSize(25).setEnablePlaceholders(false)

    @Singleton
    @Provides
    fun dataSourceFactory(api: GithubOauthApi): ReposDataSourceFactory = ReposDataSourceFactory(api)
}