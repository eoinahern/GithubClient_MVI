package ie.eoinahern.githubclient.data

import androidx.paging.DataSource
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.data.repos.ReposDataSource
import javax.inject.Inject


class ReposDataSourceFactory @Inject constructor(private val api: GithubOauthApi) :
    DataSource.Factory<Int, RepoItem>() {

    private lateinit var key: String

    @Synchronized
    override fun create(): DataSource<Int, RepoItem> = ReposDataSource(api, key)

    @Synchronized
    fun setKey(apiKey: String) {
        this.key = apiKey
    }

}