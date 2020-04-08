package ie.eoinahern.githubclient.di


import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import dagger.Module
import dagger.Provides
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.data.repos.ReposDataSource
import javax.inject.Singleton


@Module
class PagingModule {

    @Singleton
    @Provides
    fun getConfig(): PagedList.Config.Builder {
        val config = PagedList.Config.Builder()
            .setPageSize(25)
            .setEnablePlaceholders(false)

        return config
    }

    @Singleton
    @Provides
    fun dataSourceFactory(api: GithubOauthApi): DataSource.Factory<Int, RepoItem> =
        object : DataSource.Factory<Int, RepoItem>() {
            override fun create(): DataSource<Int, RepoItem> {
                return ReposDataSource(api)
            }
        }

    @Singleton
    @Provides
    fun getBuilder(
        dataSourceFactory: DataSource.Factory<Int, RepoItem>,
        config: PagedList.Config.Builder
    ): RxPagedListBuilder<Int, RepoItem> {
        return RxPagedListBuilder<Int, RepoItem>(dataSourceFactory, config.build())
    }

}