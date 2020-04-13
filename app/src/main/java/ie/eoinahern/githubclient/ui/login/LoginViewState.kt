package ie.eoinahern.githubclient.ui.login


sealed class LoginScreenViewState {
    object IntermediateState : LoginScreenViewState()
    data class CompleteState(val key: String) : LoginScreenViewState()
    data class FailureState(val throwable: Throwable?) : LoginScreenViewState()
    object ProgressState : LoginScreenViewState()
}
