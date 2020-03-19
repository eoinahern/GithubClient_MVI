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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.ui.repos.ReposActivity
import ie.eoinahern.githubclient.util.constants.CALLBACK_URL
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.constants.KEYSTORE_ALIAS
import ie.eoinahern.githubclient.util.encrypt.EncryptionUtil
import ie.eoinahern.githubclient.util.getViewModel
import ie.eoinahern.githubclient.util.viewmodel.ViewModelCreationFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*
import java.nio.charset.Charset
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), MviView<LoginIntent, LoginViewState> {

    private val authUserPublisher =
        PublishSubject.create<LoginIntent.AuthUserIntent>()


    private val checkHaveKey: PublishSubject<LoginIntent.CheckHasKey> =
        PublishSubject.create()

    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var factory: ViewModelCreationFactory

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as GithubApp).getAppComponent().inject(this)

        createViewModel()

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun createViewModel() {
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
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
            authUserPublisher.onNext(LoginIntent.AuthUserIntent(code))
        }
    }

    private fun authUserIntent(): Observable<LoginIntent.AuthUserIntent> {
        return authUserPublisher
    }

    private fun showLoading() {
        loadingLayout.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loadingLayout.visibility = View.GONE
    }

    private fun setErrorMessage(error: String) {
        messageText.text = error
    }

    private fun goToNext() {
        startActivity(Intent(this, ReposActivity::class.java))
        finish()
    }

    override fun onResume() {
        super.onResume()
        checkHaveKey()
        parseCallback()
    }

    private fun checkHaveKey() {
        checkHaveKey.onNext(LoginIntent.CheckHasKey)
    }

    override fun intents(): Observable<LoginIntent> = authUserIntent().cast(LoginIntent::class.java)

    override fun render(state: LoginViewState) {

        if (state.isProcessing) showLoading() else hideLoading()

        state.generalFail?.let { error ->
            setErrorMessage(error.message ?: "error Loading")
        } ?: setErrorMessage("")

        if (state.loginComplete) {
            goToNext()
        }
    }


    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun bind() {
        disposables += loginViewModel.states().subscribe { viewState -> render(viewState) }
        loginViewModel.processIntents(intents())
    }
}

