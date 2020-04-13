package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.filestorage.KeyStorage
import ie.eoinahern.githubclient.ui.login.LoginScreenViewState
import ie.eoinahern.githubclient.ui.login.LoginViewState
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.constants.CLIENT_SECRET
import io.reactivex.Observable
import javax.inject.Inject


class LoginInteractor @Inject constructor(
    private val api: GithubApi,
    private val keyStorage: KeyStorage
) {

    fun loginUserGetKeyFromWeb(key: String): Observable<LoginScreenViewState> {
        return api.getAuthToken(key, CLIENT_SECRET, CLIENT_ID, "")
            .map {
                keyStorage.saveUserToken(it.accessToken)
                LoginScreenViewState.CompleteState(it.accessToken)
            }
            .cast(LoginScreenViewState::class.java)
            .onErrorReturn { LoginScreenViewState.FailureState(it) }
    }

    /**
     * refactor LoginViewState object.
     * can be modelled as a sealed class now
     *
     */

    fun getLocalSavedKey(): Observable<LoginScreenViewState> {
        return keyStorage.checkHasLocalToken().map {
            LoginScreenViewState.CompleteState(it)
        }.cast(LoginScreenViewState::class.java)
            .onErrorReturn {
                LoginScreenViewState.IntermediateState
            }
    }
}