package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ReposViewModel @Inject constructor(private val api: GithubApi) : ViewModel(),
    MviViewModel<ReposIntent, ReposViewState> {

    private fun convertToAction(intent: ReposIntent): ReposAction {
        return when (intent) {
            is ReposIntent.LoadReposIntent -> {
                ReposAction.LoginAction(intent.apiKey)
            }
        }
    }

    override fun processIntents(obs: Observable<ReposIntent>) {

    }

    override fun states(): Observable<ReposViewState> {
        return Observable.just(ReposViewState.getDefault())
    }

}