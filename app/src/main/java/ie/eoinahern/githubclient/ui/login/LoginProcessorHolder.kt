package ie.eoinahern.githubclient.ui.login

import android.util.Log
import ie.eoinahern.githubclient.data.login.LoginRepository
import ie.eoinahern.githubclient.ui.login.LoginResult.CheckHasKeyResult.Failure
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.IllegalArgumentException
import javax.inject.Inject

class LoginProcessorHolder @Inject constructor(
    private val repo: LoginRepository,
    private val schedulerProvider: SchedulerProvider
) {

    private val loginProcessor =
        ObservableTransformer<LoginAction.AuthUserAction, LoginResult.LoginAttemptResult> { action ->
            action.observeOn(schedulerProvider.getIOSchecduler())
                .flatMap { auth ->
                    repo.getUserToken(auth.code)
                }.map { key ->
                    LoginResult.LoginAttemptResult.Success(key)
                }
                .cast(LoginResult.LoginAttemptResult::class.java)
                .onErrorReturn(LoginResult.LoginAttemptResult::Failure)
                .observeOn(schedulerProvider.getMainSchedulers())
        }

    private val checkLocalKeyProcessor =
        ObservableTransformer<LoginAction.CheckHasKeyAction, LoginResult.CheckHasKeyResult> { action ->
            action.observeOn(schedulerProvider.getIOSchecduler())
                .flatMap { _ ->
                    repo.checkHasLocalToken()
                }.map { key ->
                    LoginResult.CheckHasKeyResult.Success(key)
                }
                .cast(LoginResult.CheckHasKeyResult::class.java)
                .onErrorReturn { t ->
                    Log.d("error", t.message)
                    Failure
                }
                .observeOn(schedulerProvider.getMainSchedulers())
                .startWith(LoginResult.CheckHasKeyResult.Processing)
        }

    internal val actionProcessor = ObservableTransformer<LoginAction, LoginResult> { actions ->

        Observable.merge(
                actions.ofType(LoginAction.AuthUserAction::class.java).compose(loginProcessor),
                actions.ofType(LoginAction.CheckHasKeyAction::class.java)
                    .compose(checkLocalKeyProcessor)
            )
            .mergeWith(actions.filter { itm ->
                itm !is LoginAction.CheckHasKeyAction && itm !is LoginAction.AuthUserAction
            }.flatMap { _ ->
                Observable.error<LoginResult>(IllegalArgumentException("error"))
            })

    }
}