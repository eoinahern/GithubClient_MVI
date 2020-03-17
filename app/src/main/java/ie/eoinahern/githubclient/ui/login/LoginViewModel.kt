package ie.eoinahern.githubclient.ui.login

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import ie.eoinahern.githubclient.ui.login.LoginResult.LoginAttemptResult.*

class LoginViewModel @Inject constructor(private val actionProcessorHolder: LoginProcessorHolder) :
    ViewModel(),
    MviViewModel<LoginIntent, LoginViewState> {

    private val intentsSubject: PublishSubject<LoginIntent> = PublishSubject.create()
    private val statesObservable: Observable<LoginViewState> = compose()

    override fun processIntents(obs: Observable<LoginIntent>) {
        obs.subscribe(intentsSubject)
    }

    override fun states(): Observable<LoginViewState> = statesObservable


    private fun getActionFromIntent(intent: LoginIntent): LoginAction {
        return when (intent) {
            is LoginIntent.AuthUserIntent -> LoginAction.AuthUserAction(intent.code)
            is LoginIntent.CheckHasKey -> {
                LoginAction.AuthUserAction("dddd")
            }
        }
    }

    private fun compose(): Observable<LoginViewState> {
        return intentsSubject.map { intent ->
                getActionFromIntent(intent)
            }.compose(actionProcessorHolder.actionProcessor)
            .scan(LoginViewState.getInitState(), reducer)
            .distinctUntilChanged()
    }


    companion object {

        val reducer = BiFunction { previousVState: LoginViewState, result: LoginResult ->
            when (result) {
                is LoginResult.LoginAttemptResult -> reduceAuthUser(previousVState, result)
            }
        }


        private fun reduceAuthUser(
            previousState: LoginViewState,
            result: LoginResult.LoginAttemptResult
        ): LoginViewState {
            return when (result) {
                is Processing -> {
                    previousState.copy(
                        isProcessing = true,
                        generalFail = null
                    )
                }
                is Success -> {
                    previousState.copy(
                        isProcessing = false,
                        generalFail = null,
                        loginComplete = true
                    )

                }
                is Failure -> {
                    previousState.copy(
                        isProcessing = false,
                        generalFail = result.error
                    )
                }
            }
        }
    }

}