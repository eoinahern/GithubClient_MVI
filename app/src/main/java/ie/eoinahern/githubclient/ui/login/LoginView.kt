package ie.eoinahern.githubclient.ui.login

import io.reactivex.Observable

interface LoginView {
    fun render(view: LoginScreenViewState)
    fun loginIntent(): Observable<String>
    fun getCheckHasKey(): Observable<Unit>
}