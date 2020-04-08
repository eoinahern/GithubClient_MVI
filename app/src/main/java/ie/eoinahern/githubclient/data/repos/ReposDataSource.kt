package ie.eoinahern.githubclient.data.repos


import androidx.paging.PageKeyedDataSource
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import ie.eoinahern.githubclient.util.constants.PAGE_SIZE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReposDataSource constructor(private val api: GithubOauthApi, private val key: String) :
    PageKeyedDataSource<Int, RepoItem>() {

    private var pageNumber: Int = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoItem>
    ) {

        api.getRepos(key, pageNumber, PAGE_SIZE).enqueue(object :
            Callback<List<RepoItem>> {

            override fun onFailure(call: Call<List<RepoItem>>, t: Throwable) {
                println(t.printStackTrace())
                throw Exception(t.message)
            }

            override fun onResponse(
                call: Call<List<RepoItem>>,
                response: Response<List<RepoItem>>
            ) {

                if (response.isSuccessful) {
                    val list = response.body() ?: listOf()

                    callback.onResult(list, null, pageNumber + 1)
                } else {
                    throw Exception("unsuccsessful request!!!  code : ${response.code()}")
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {

        val currentPage = params.key

        api.getRepos(key, currentPage, PAGE_SIZE).enqueue(object :
            Callback<List<RepoItem>> {

            override fun onFailure(call: Call<List<RepoItem>>, t: Throwable) {
                println(t.printStackTrace())
                throw Exception(t.message)
            }

            override fun onResponse(
                call: Call<List<RepoItem>>,
                response: Response<List<RepoItem>>
            ) {

                if (response.isSuccessful) {
                    val list = response.body() ?: listOf()
                    callback.onResult(list, currentPage + 1)
                } else {
                    throw Exception("unsucsessful request!!!  code : ${response.code()}")
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
    }
}