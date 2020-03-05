package ie.eoinahern.githubclient.ui.login

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
            action.flatMap { auth ->
                repo.getUserToken(auth.code)
            }.map { apiKey ->
                println("my api key!!! $apiKey")
                LoginResult.LoginAttemptResult.Success
            }
                .cast(LoginResult.LoginAttemptResult::class.java)
                .onErrorReturn(LoginResult.LoginAttemptResult::Failure)
                .subscribeOn(schedulerProvider.getIOSchecduler())
                .observeOn(schedulerProvider.getIOSchecduler())
                .startWith(LoginResult.LoginAttemptResult.Processing)
        }


    internal val actionProcessor =
        ObservableTransformer<LoginAction, LoginResult> { actions ->
            actions.ofType(LoginAction.AuthUserAction::class.java).compose(loginProcessor)
        }


}