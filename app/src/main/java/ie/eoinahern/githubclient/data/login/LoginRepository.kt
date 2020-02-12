package ie.eoinahern.githubclient.data.login

import android.content.SharedPreferences
import ie.eoinahern.githubclient.data.GithubApi
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val api: GithubApi, private val sharedPreferences: SharedPreferences,
    private val sharedPrefsEdit: SharedPreferences.Editor
) {




}