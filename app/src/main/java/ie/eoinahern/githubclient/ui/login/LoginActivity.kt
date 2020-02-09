package ie.eoinahern.githubclient.ui.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import ie.eoinahern.githubclient.util.constants.CALLBACK_URL
import ie.eoinahern.githubclient.util.constants.CLIENT_ID
import ie.eoinahern.githubclient.util.getViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), MviView<LoginIntent, LoginViewState> {

    private val emailIntentPublisher = PublishSubject.create<LoginIntent.EmailIntent>()
    private val passwordIntentPublisher = PublishSubject.create<LoginIntent.PasswordIntent>()
    private val attemptLoginIntentPublisher =
        PublishSubject.create<LoginIntent.AttemptLoginIntent>()


    private val loginViewModel: LoginViewModel by getViewModel { LoginViewModel() }

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "https://github.com/login/oauth/authorize" +
                        "?client_id=${CLIENT_ID}" + "&scope=repo&redirect_url=$CALLBACK_URL"
            )
        )

        startActivity(intent)
    }

    override fun intents(): Observable<LoginIntent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(state: LoginViewState) {

    }

    /*private fun combineIntentOutput(): Observable<LoginIntent> {

    }*/

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
        //loginViewModel.processIntents(combineIntentOutput())
    }
}
