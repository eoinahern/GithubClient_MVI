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
    fun getConfig(): PagedList.Config.Builder =
        PagedList.Config.Builder().setPageSize(25).setEnablePlaceholders(false)

    @Singleton
    @Provides
    fun dataSourceFactory(api: GithubOauthApi): MyFactory = MyFactory(api)

    class MyFactory constructor(private val api: GithubOauthApi) :
        DataSource.Factory<Int, RepoItem>() {

        private lateinit var key: String


        @Synchronized
        override fun create(): DataSource<Int, RepoItem> = ReposDataSource(api, key)

        @Synchronized
        fun setKey(apiKey: String) {
            this.key = apiKey
        }
    }
}