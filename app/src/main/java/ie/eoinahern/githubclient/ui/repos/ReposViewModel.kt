package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import ie.eoinahern.githubclient.ui.repos.ReposResult.LoadResposResult


class ReposViewModel @Inject constructor(private val reposProcessHolder: ReposProcessorHolder) :
    ViewModel(),
    MviViewModel<ReposIntent, ReposViewState> {


    private val intentsSubject = PublishSubject.create<ReposIntent>()
    private val statesObs: Observable<ReposViewState> = compose()

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
        }.compose(reposProcessHolder.actionProcessor)
            .scan(ReposViewState.getDefault(), reducer)
            .distinctUntilChanged()
    }

    override fun states(): Observable<ReposViewState> = statesObs

    companion object {

        val reducer = BiFunction { viewState: ReposViewState, result: ReposResult ->
            when (result) {
                is LoadResposResult -> reduceGetRepos(viewState, result)
            }
        }

        private fun reduceGetRepos(
            previousViewState: ReposViewState,
            result: LoadResposResult
        ): ReposViewState {

            return when (result) {
                is LoadResposResult.Processing -> {
                    previousViewState.copy(isProcessing = true, data = listOf(), error = null)
                }
                is LoadResposResult.Success -> {
                    previousViewState.copy(isProcessing = false, data = result.data, error = null)
                }
                is LoadResposResult.LoadError -> {
                    previousViewState.copy(isProcessing = false, error = result.err)
                }
            }
        }

    }

}