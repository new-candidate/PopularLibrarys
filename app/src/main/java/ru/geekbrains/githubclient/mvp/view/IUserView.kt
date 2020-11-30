package ru.geekbrains.githubclient.mvp.view

import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IUserView : MvpView {
    fun init()
    fun setLogin(login: String?)
    fun loadImage(url: String?)
    fun updateList()
    fun sendRepository(repository: GithubUserRepo?)
}