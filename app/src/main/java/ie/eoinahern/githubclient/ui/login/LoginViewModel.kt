package ie.eoinahern.githubclient.ui.login

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable

class LoginViewModel : ViewModel(), MviViewModel<LoginIntent, LoginViewState> {


    override fun processIntents(obs: Observable<LoginIntent>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun states(): Observable<LoginViewState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}