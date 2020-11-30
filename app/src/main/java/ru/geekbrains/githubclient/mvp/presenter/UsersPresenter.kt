package ru.geekbrains.githubclient.mvp.presenter

import ru.geekbrains.githubclient.mvp.model.dataSource.DataSourceRemote
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.repository.UsersRepo
import ru.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.githubclient.mvp.view.itemsView.IUserItemView
import ru.geekbrains.githubclient.mvp.view.IUsersView
import ru.geekbrains.githubclient.navigation.Screens
import ru.geekbrains.githubclient.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router


class UsersPresenter(
    private val router: Router,
    private val repository: UsersRepo = UsersRepo(DataSourceRemote()),
    private val schedulersProvider: SchedulerProvider = SchedulerProvider(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) :
    MvpPresenter<IUsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users =
            mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]

            view.setLogin(user.login)
            view.loadAvatar(user.avatar_url)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = {
            router.navigateTo(Screens.UserScreen())

            val user = usersListPresenter.users[it.pos]
            viewState.sendUser(user)
        }
    }

    private fun loadData() {
        compositeDisposable.add(
            repository.getUsers()
                .observeOn(schedulersProvider.ui())
                .subscribe({ t ->
                    val users = mutableListOf<GithubUser>()
                    users.addAll(t)

                    usersListPresenter.users.addAll(users)

                    viewState.updateList()
                }, { e ->

                })
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}