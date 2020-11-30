package ru.geekbrains.githubclient.mvp.view

import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IUsersView : MvpView {
    fun init()
    fun updateList()
    fun sendUser(user: GithubUser)
}
