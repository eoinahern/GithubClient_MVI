package ie.eoinahern.githubclient.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.presenter.LoginPresenter
import ie.eoinahern.githubclient.ui.repos.ReposActivity
import ie.eoinahern.githubclient.util.constants.CALLBACK_URL
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.constants.KEYSTORE_ALIAS
import ie.eoinahern.githubclient.util.encrypt.EncryptionUtil
import ie.eoinahern.githubclient.util.getViewModel
import ie.eoinahern.githubclient.util.viewmodel.ViewModelCreationFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*
import java.nio.charset.Charset
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    private val authUserPublisher =
        PublishSubject.create<String>()

    private val checkHaveKeyPublisher: PublishSubject<Unit> =
        PublishSubject.create()

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as GithubApp).getAppComponent().inject(this)

        loginButton.setOnClickListener {
            loginUser()
        }

        presenter.setView(this)
    }

    private fun loginUser() {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "https://github.com/login/oauth/authorize" +
                        "?client_id=${CLIENT_ID}" + "&scope=repo&redirect_url=$CALLBACK_URL"
            )
        )

        startActivity(intent)
    }

    private fun parseCallback() {
        val uri = intent.data

        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            val code = uri.getQueryParameter("code") ?: ""
            authUserPublisher.onNext(code)
        }
    }

    private fun getAuthUserIntent(): Observable<String> {
        return authUserPublisher
    }

    override fun getCheckHasKey(): Observable<Unit> = checkHaveKeyPublisher


    private fun showLoading() {
        loadingLayout.isVisible = true
    }

    private fun hideLoading() {
        loadingLayout.isVisible = false
    }

    private fun setErrorMessage(error: String) {
        messageText.text = error
    }

    private fun goToNext(key: String) {
        val intent = Intent(this, ReposActivity::class.java)
        intent.putExtra("key", key)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        checkHasLoginKey()
        parseCallback()
    }

    private fun checkHasLoginKey() {
        checkHaveKeyPublisher.onNext(Unit)
    }

    /*override fun intents(): Observable<LoginIntent> {
        return Observable.merge(
            getAuthUserIntent(),
            getCheckHasKey()
        ).cast(LoginIntent::class.java)
    }*/

    override fun loginIntent(): Observable<String> {
        return authUserPublisher
    }

    override fun render(state: LoginScreenViewState) {


        when (state) {
            is LoginScreenViewState.ProgressState -> {

            }
            is LoginScreenViewState.IntermediateState -> {

            }
            is LoginScreenViewState.FailureState -> {

            }
            is LoginScreenViewState.CompleteState -> {

            }
        }

        /*if (state.isProcessing) showLoading() else hideLoading()

        state.generalFail?.let { error ->
            setErrorMessage(error.message ?: "error Loading")
        } ?: setErrorMessage("")

        if (state.visibleLoginButton) {
            loginButton.isVisible = true
        }

        if (state.loginComplete && !state.key.isNullOrEmpty()) {
            goToNext(state.key)
        }*/
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }
}

