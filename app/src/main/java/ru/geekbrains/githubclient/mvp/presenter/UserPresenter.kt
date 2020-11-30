package ru.geekbrains.githubclient.mvp.presenter

import ru.geekbrains.githubclient.mvp.model.dataSource.DataSourceRemote
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.repository.UsersRepo
import ru.geekbrains.githubclient.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.githubclient.mvp.view.itemsView.IRepositoryItemView
import ru.geekbrains.githubclient.mvp.view.IUserView
import ru.geekbrains.githubclient.navigation.Screens
import ru.geekbrains.githubclient.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val router: Router,
    private val repository: UsersRepo = UsersRepo(
        DataSourceRemote()
    ),
    private val schedulersProvider: SchedulerProvider = SchedulerProvider(),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MvpPresenter<IUserView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories =
            mutableListOf<GithubUserRepo>()

        override var itemClickListener: ((IRepositoryItemView) -> Unit)? = null

        override fun bindView(view: IRepositoryItemView) {
            val repository = repositories[view.pos]

            view.setRepository(repository.name)
        }

        override fun getCount(): Int = repositories.size
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()

        repositoriesListPresenter.itemClickListener = {
            router.navigateTo(Screens.RepositoryScreen())

            val repository = repositoriesListPresenter.repositories[it.pos]
            viewState.sendRepository(repository)
        }
    }

    val repositoriesListPresenter = RepositoryListPresenter()

    private fun loadData(user: GithubUser) {
        compositeDisposable.add(
            repository.getUserRepository(user.repos_url)
                .observeOn(schedulersProvider.ui())
                .subscribe { t ->
                    val repositories = mutableListOf<GithubUserRepo>()
                    repositories.addAll(t)

                    repositoriesListPresenter.repositories.addAll(repositories)


                    viewState.updateList()
                    viewState.setLogin(user.login)
                    viewState.loadImage(user.avatar_url)
                })
    }

    fun showUserInfo(user: GithubUser) {
        loadData(user)
    }

    fun backPressed(): Boolean {
        router.replaceScreen(Screens.UsersScreen())
        return true
    }
}