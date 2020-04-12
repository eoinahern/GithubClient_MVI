package ie.eoinahern.githubclient.ui.login

import io.reactivex.Observable

interface LoginView {
    fun render()
    fun loginIntent(): Observable<String>
}