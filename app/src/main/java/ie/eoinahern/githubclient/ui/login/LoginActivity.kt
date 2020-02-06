package ie.eoinahern.githubclient.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), MviView<LoginIntent, LoginViewState> {

    private val emailIntentPublisher = PublishSubject.create<LoginIntent.EmailIntent>()
    private val passwordIntentPublisher = PublishSubject.create<LoginIntent.PasswordIntent>()
    private val attemptLoginIntentPublisher =
        PublishSubject.create<LoginIntent.AttemptLoginIntent>()

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)

    }

    override fun intents(): Observable<LoginIntent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(state: LoginViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    fun bind() {

    }
}
