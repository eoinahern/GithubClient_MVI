package ie.eoinahern.githubclient.data

import ie.eoinahern.githubclient.data.filestorage.KeyStorage
import ie.eoinahern.githubclient.ui.login.LoginViewState
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.constants.CLIENT_SECRET
import io.reactivex.Observable
import javax.inject.Inject


class LoginInteractor @Inject constructor(
    private val api: GithubApi,
    private val keyStorage: KeyStorage
) {

    fun loginUserGetKeyFromWeb(key: String): Observable<LoginViewState> {
        return api.getAuthToken(key, CLIENT_SECRET, CLIENT_ID, "")
            .map {
                LoginViewState.getInitState()
                    .copy(key = it.accessToken, isProcessing = false, loginComplete = true)
            }.onErrorReturn { LoginViewState.getInitState().copy(false, generalFail = it) }
    }

    /**
     * refactor LoginViewState object.
     * can be modelled as a sealed class now
     *
     */

    fun getLocalSavedKey(): Observable<LoginViewState> {
        return keyStorage.checkHasLocalToken().map {
            LoginViewState.getInitState().copy(false, generalFail = null, key = it)
        }.onErrorReturn {
            LoginViewState.getInitState().copy(false, generalFail = it, visibleLoginButton = true)
        }
    }
}