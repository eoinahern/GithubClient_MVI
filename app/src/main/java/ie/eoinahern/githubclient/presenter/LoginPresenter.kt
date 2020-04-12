package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.LoginInteractor
import ie.eoinahern.githubclient.ui.login.LoginView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class LoginPresenter @Inject constructor(private val LoginInteractor: LoginInteractor) {

    private lateinit var view: LoginView
    private val disposables = CompositeDisposable()

    fun setView(loginView: LoginView) {
        view = loginView
    }


    fun loginUser() {

    }


    fun unbind() {
        
    }
}