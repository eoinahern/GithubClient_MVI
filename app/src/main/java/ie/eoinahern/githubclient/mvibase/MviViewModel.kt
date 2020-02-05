package ie.eoinahern.githubclient.mvibase

import io.reactivex.Observable


interface MviViewModel<I : MviIntent, V : MviViewState> {
    fun processIntents(obs: Observable<I>)
    fun states(): Observable<V>
}