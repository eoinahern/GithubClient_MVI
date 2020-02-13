package ie.eoinahern.githubclient.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ie.eoinahern.githubclient.GithubApp
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.util.constants.CALLBACK_URL
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.encrypt.EncryptionUtil
import ie.eoinahern.githubclient.util.getViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), MviView<LoginIntent, LoginViewState> {

    private val attemptLoginIntentPublisher =
        PublishSubject.create<LoginIntent.AuthUserIntent>()

    private val loginViewModel: LoginViewModel by getViewModel { LoginViewModel() }

    @Inject
    lateinit var encryptionUtil: EncryptionUtil

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as GithubApp).getAppComponent().inject(this)


        //check already have legit key for getting data.
        // if not allow login button press.
        // else navigate to repos activity.
        // on first login call api. save and encrypt key.
        // go to next screen.
        // if fails show error. allow retry

        testEncryption()

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun testEncryption() {

        val result = encryptionUtil.encrypt("test!!!!!".toByteArray(Charsets.UTF_8))
        val decrypted = encryptionUtil.decrypt(result)
        val decryptedString = String(decrypted, Charsets.UTF_8)
        Log.d("decrypted", decryptedString)
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
            val code = uri.getQueryParameter("code")
        }
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

    override fun onResume() {
        super.onResume()
        parseCallback()
    }

    override fun intents(): Observable<LoginIntent> {
        return Observable.just(LoginIntent.AuthUserIntent("email"))
    }

    override fun render(state: LoginViewState) {
        //do viewstate stuff!!!
    }

    private fun combineIntentOutput(): Observable<LoginIntent> {
        return Observable.just(LoginIntent.AuthUserIntent("email"))
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
        loginViewModel.processIntents(combineIntentOutput())
    }
}
