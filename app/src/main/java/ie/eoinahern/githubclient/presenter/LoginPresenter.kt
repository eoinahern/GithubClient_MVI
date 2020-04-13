package ie.eoinahern.githubclient.presenter

import ie.eoinahern.githubclient.data.LoginInteractor
import ie.eoinahern.githubclient.ui.login.LoginScreenViewState
import ie.eoinahern.githubclient.ui.login.LoginView
import ie.eoinahern.githubclient.ui.login.LoginViewState
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val schedulerProvider: SchedulerProvider
) {

    private lateinit var view: LoginView
    private val disposables = CompositeDisposable()

    fun setView(loginView: LoginView) {
        view = loginView
        disposables += observeCheckHasKey()
        disposables += observeLoginUser()
    }

    private fun observeLoginUser() = view.loginIntent()
        .observeOn(schedulerProvider.getIOSchecduler())
        .flatMap { loginInteractor.loginUserGetKeyFromWeb(it) }
        .observeOn(schedulerProvider.getIOSchecduler())
        .subscribe { view.render(it) }

    private fun observeCheckHasKey() = view.getCheckHasKey()
        .observeOn(schedulerProvider.getIOSchecduler())
        .flatMap { loginInteractor.getLocalSavedKey() }
        .observeOn(schedulerProvider.getMainSchedulers())
        .onErrorReturn { LoginScreenViewState.IntermediateState }
        .startWith(LoginScreenViewState.ProgressState)
        .subscribe { view.render(it) }


    fun unbind() {
        disposables.clear()
    }
}