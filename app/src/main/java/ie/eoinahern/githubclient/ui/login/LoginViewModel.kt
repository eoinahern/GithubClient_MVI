package ie.eoinahern.githubclient.ui.login

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.data.login.LoginRepository
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepo: LoginRepository) : ViewModel(),
    MviViewModel<LoginIntent, LoginViewState> {

    private val intentsSubject: PublishSubject<LoginIntent> = PublishSubject.create()


    override fun processIntents(obs: Observable<LoginIntent>) {
        obs.subscribe(intentsSubject)
    }

    override fun states(): Observable<LoginViewState> {
        return Observable.just(LoginViewState.getInitState())
    }

}