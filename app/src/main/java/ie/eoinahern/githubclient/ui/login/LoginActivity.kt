package ie.eoinahern.githubclient.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.mvibase.MviView
import io.reactivex.Observable

class LoginActivity : AppCompatActivity(), MviView<LoginIntent, LoginViewState> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    override fun intents(): Observable<LoginIntent> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(state: LoginViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
