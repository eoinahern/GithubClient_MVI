package ie.eoinahern.githubclient.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(crossinline provider: () -> T): Lazy<T> {

    return lazy {
        val factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val vm = provider.invoke()
                return vm as T
            }
        }

        ViewModelProviders.of(this, factory).get(T::class.java)
    }
}