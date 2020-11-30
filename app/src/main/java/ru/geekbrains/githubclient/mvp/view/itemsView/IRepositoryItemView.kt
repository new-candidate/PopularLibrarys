package ru.geekbrains.githubclient.mvp.view.itemsView

interface IRepositoryItemView : IItemView {

    fun setRepository(repository: String?)
}