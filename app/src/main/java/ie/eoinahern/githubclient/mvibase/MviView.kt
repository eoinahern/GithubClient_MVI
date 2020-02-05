package ie.eoinahern.githubclient.mvibase

import io.reactivex.Observable

interface MviView<I : MviIntent, V : MviViewState> {
    fun intents(): Observable<I>
    fun render(state: V)
}