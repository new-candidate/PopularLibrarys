package ru.geekbrains.githubclient.mvp.presenter

import ru.geekbrains.githubclient.mvp.view.IMainView
import ru.geekbrains.githubclient.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<IMainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }
}