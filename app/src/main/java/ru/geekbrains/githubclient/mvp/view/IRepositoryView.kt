package ru.geekbrains.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IRepositoryView : MvpView {
    fun setName(name: String?)
    fun isPrivate(isPrivate: Boolean?)
    fun setForks(count: Int?)
}