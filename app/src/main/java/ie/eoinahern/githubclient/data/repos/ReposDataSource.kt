package ie.eoinahern.githubclient.data.repos


import androidx.paging.PageKeyedDataSource
import ie.eoinahern.githubclient.data.GithubOauthApi
import ie.eoinahern.githubclient.data.model.RepoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ReposDataSource @Inject constructor(private val api: GithubOauthApi) :
    PageKeyedDataSource<Int, RepoItem>() {

    private var pageNumber: Int = 1
    private lateinit var apiKey: String

    fun setKey(euIn: String) {
        this.apiKey = euIn
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepoItem>
    ) {

        api.getRepos(apiKey, pageNumber, params.requestedLoadSize).enqueue(object :
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
                    val list = response.body()
                    callback.onResult(list as MutableList<RepoItem>, null, pageNumber + 1)
                } else {
                    throw Exception("unsuccsessful request!!!  code : ${response.code()}")
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {


        val currentPage = params.key
        val pageSize = params.requestedLoadSize

        api.getRepos(apiKey, currentPage, pageSize).enqueue(object :
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
                    val list = response.body()
                    callback.onResult(list as MutableList<RepoItem>, currentPage + 1)
                } else {
                    throw Exception("unsucsessful request!!!  code : ${response.code()}")
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoItem>) {
        println("in load before?????")
    }

}