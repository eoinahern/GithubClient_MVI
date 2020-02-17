package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import javax.inject.Inject


class ReposViewModel @Inject constructor(private val api: GithubApi) : ViewModel(),
    MviViewModel<ReposIntent, ReposViewState> {


    override fun processIntents(obs: Observable<ReposIntent>) {

    }

    override fun states(): Observable<ReposViewState> {
        return Observable.just(ReposViewState.getDefault())
    }

}