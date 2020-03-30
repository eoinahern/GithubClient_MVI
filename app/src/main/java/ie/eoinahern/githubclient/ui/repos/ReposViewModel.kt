package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.data.GithubApi
import ie.eoinahern.githubclient.data.repos.ReposRepository
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ReposViewModel @Inject constructor(private val reposProcessHolder: ReposProcessorHolder) :
    ViewModel(),
    MviViewModel<ReposIntent, ReposViewState> {

    private val statesObs: Observable<ReposViewState> = compose()
    private val intentsSubject = PublishSubject.create<ReposIntent>()

    private fun convertToAction(intent: ReposIntent): ReposAction {
        return when (intent) {
            is ReposIntent.LoadReposIntent -> {
                ReposAction.LoadRepos(intent.apiKey)
            }
        }
    }

    override fun processIntents(obs: Observable<ReposIntent>) {
        obs.subscribe(intentsSubject)
    }

    private fun compose(): Observable<ReposViewState> {
        return intentsSubject.map { intent ->
            convertToAction(intent)
        }
    }

    override fun states(): Observable<ReposViewState> = statesObs

    companion object {

    }

}