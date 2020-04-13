package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.LoginInteractor
import ie.eoinahern.githubclient.ui.login.LoginView
import ie.eoinahern.githubclient.ui.login.LoginViewState
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val LoginInteractor: LoginInteractor,
    private val schedulerProvider: SchedulerProvider
) {

    private lateinit var view: LoginView
    private val disposables = CompositeDisposable()

    fun setView(loginView: LoginView) {
        view = loginView

        disposables += observeCheckHasKey()
        disposables += observeCheckHasKey()
    }


    fun observeLoginUser() = view.loginIntent()

    fun observeCheckHasKey() = view.getCheckHasKey()
        .observeOn(schedulerProvider.getIOSchecduler())
        .map { }
        .observeOn(schedulerProvider.getMainSchedulers())
        .subscribe { view.render(LoginViewState.getInitState()) }


    fun unbind() {
        disposables.clear()
    }
}