package ie.eoinahern.githubclient.ui.login

import io.reactivex.Observable

interface LoginView {
    fun render(view: LoginViewState)
    fun loginIntent(): Observable<String>
}