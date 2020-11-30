package ru.geekbrains.githubclient.navigation

import androidx.fragment.app.Fragment
import ru.geekbrains.githubclient.ui.fragment.RepositoryFragment
import ru.geekbrains.githubclient.ui.fragment.UserFragment
import ru.geekbrains.githubclient.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance()
    }

    class RepositoryScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = RepositoryFragment.newInstance()
    }
}