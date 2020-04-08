package ie.eoinahern.githubclient.data.repos

import androidx.paging.PageKeyedDataSource
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import javax.inject.Inject

class ReposDataSource @Inject constructor(private val api: GithubOauthApi) :
    PageKeyedDataSource<Int, RepoItem>() {


    // maybe required provisionally
    private var pageNumber: Int = 1
    private var pageItems: Int = 25

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoItem>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
        TODO("Not yet implemented")
    }

}