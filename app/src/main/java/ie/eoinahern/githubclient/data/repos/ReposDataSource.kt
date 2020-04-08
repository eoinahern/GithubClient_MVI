package ie.eoinahern.githubclient.data.repos

import androidx.paging.PageKeyedDataSource
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import javax.inject.Inject

class ReposDataSource @Inject constructor(private val api: GithubOauthApi) :
    PageKeyedDataSource<Int, RepoItem>() {

    private var pageNumber: Int = 1
    private var pageItems: Int = 25
    private lateinit var apiKey: String

    fun setKey(euIn: String) {
        this.apiKey = euIn
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoItem>
    ) {

        val items = api.getRepos(apiKey, pageNumber, pageItems)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
        TODO("Not yet implemented")
    }

}