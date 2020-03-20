package ie.eoinahern.githubclient.ui.login

import android.util.Log
import ie.eoinahern.githubclient.data.login.LoginRepository
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
                .startWith(LoginResult.LoginAttemptResult.Processing)
        }


    internal val actionProcessor =
        ObservableTransformer<LoginAction, LoginResult> { actions ->
            actions.ofType(LoginAction.AuthUserAction::class.java).compose(loginProcessor)
        }
}