package ie.eoinahern.githubclient.ui.login

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val actionProcessorHolder: LoginProcessorHolder) : ViewModel(),
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
        }
    }

    private fun compose(): Observable<LoginViewState> {
        return intentsSubject.map{
            intent -> getActionFromIntent(intent)
        }.compose()
    }

}