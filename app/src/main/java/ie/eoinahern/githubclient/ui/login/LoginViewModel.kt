package ie.eoinahern.githubclient.ui.login

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable

class LoginViewModel : ViewModel(), MviViewModel<LoginIntent, LoginViewState> {


    override fun processIntents(obs: Observable<LoginIntent>) {

    }

    override fun states(): Observable<LoginViewState> {
        return Observable.just(LoginViewState.getInitState())
    }




}