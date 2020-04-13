package ie.eoinahern.githubclient.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ie.eoinahern.githubclient.ui.repos.ReposViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginModel(loginViewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ReposViewModel::class)
    abstract fun reposViewModel(reposViewModel: ReposViewModel): ViewModel
}