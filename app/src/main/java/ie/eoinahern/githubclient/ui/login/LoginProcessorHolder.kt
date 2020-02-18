package ie.eoinahern.githubclient.ui.login

import ie.eoinahern.githubclient.data.login.LoginRepository
import ie.eoinahern.githubclient.util.schedulers.SchedulerProvider
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class LoginProcessorHolder @Inject constructor(
    private val repo: LoginRepository,
    private val schedulerProvider: SchedulerProvider
) {






    internal val actionProcessor = ObservableTransformer<LoginAction, LoginResult> {

        actions ->
    }


}