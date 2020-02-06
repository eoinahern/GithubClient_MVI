package ie.eoinahern.githubclient.ui.repos

import androidx.lifecycle.ViewModel
import ie.eoinahern.githubclient.mvibase.MviViewModel
import io.reactivex.Observable


class ReposViewModel : ViewModel(), MviViewModel<ReposIntent, ReposViewState> {


    override fun processIntents(obs: Observable<ReposIntent>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun states(): Observable<ReposViewState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}