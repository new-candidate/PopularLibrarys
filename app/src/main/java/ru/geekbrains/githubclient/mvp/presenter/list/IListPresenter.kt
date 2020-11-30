package ru.geekbrains.githubclient.mvp.presenter.list

import ru.geekbrains.githubclient.mvp.view.itemsView.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
