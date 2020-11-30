package ru.geekbrains.githubclient.mvp.view.itemsView

interface IUserItemView : IItemView {

    fun setLogin(text: String?)

    fun loadAvatar(url: String?)
}
