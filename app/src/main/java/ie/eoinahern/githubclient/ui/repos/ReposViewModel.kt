package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.ui.repos.ReposResult.LoadResposResult
import io.reactivex.functions.BiFunction
import javax.inject.Inject


class ReposViewModel @Inject constructor() :
    ViewModel() {

    private fun convertToAction(intent: ReposIntent): ReposAction {
        return when (intent) {
            is ReposIntent.LoadReposIntent -> {
                ReposAction.LoadRepos(intent.apiKey)
            }
        }
    }

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
                    previousViewState.copy(isProcessing = true, data = null, error = null)
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