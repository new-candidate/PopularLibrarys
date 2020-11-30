package ru.geekbrains.githubclient.mvp.presenter

import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.view.IRepositoryView
import moxy.MvpPresenter

class RepositoryPresenter() : MvpPresenter<IRepositoryView>() {

    fun showRepositoryInfo(repository: GithubUserRepo?) {
        val name = repository?.name
        val isPrivate = repository?.private
        val forks = repository?.forks

        viewState.setName(name)
        viewState.isPrivate(isPrivate)
        viewState.setForks(forks)
    }
}