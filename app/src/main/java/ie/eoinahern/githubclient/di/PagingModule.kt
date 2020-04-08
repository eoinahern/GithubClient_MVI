package ie.eoinahern.githubclient.di


import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import dagger.Module
import dagger.Provides
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.data.repos.ReposDataSource
import io.reactivex.Observable
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
    fun dataSourceFactory(api: GithubOauthApi): MyFactory = MyFactory(api)


    @Singleton
    @Provides
    fun getBuilder(
        dataSourceFactory: DataSource.Factory<Int, RepoItem>,
        config: PagedList.Config.Builder
    ): Observable<PagedList<RepoItem>> =
        RxPagedListBuilder<Int, RepoItem>(dataSourceFactory, config.build()).buildObservable()

    class MyFactory constructor(private val api: GithubOauthApi) :
        DataSource.Factory<Int, RepoItem>() {

        private lateinit var key: String

        override fun create(): DataSource<Int, RepoItem> {
            val source = ReposDataSource(api)
            source.setKey(this.key)
            return source
        }

        fun setKey(apiKey: String) {
            this.key = apiKey
        }

    }
}