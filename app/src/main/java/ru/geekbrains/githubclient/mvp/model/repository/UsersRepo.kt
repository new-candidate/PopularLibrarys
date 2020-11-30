package ru.geekbrains.githubclient.mvp.model.repository

import ru.geekbrains.githubclient.mvp.model.dataSource.IDataSource
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

class UsersRepo(
    private val dataSource: IDataSource,
) : IUsersRepo {

    override fun getUsers(): Single<List<GithubUser>> =
        dataSource.getUsers()

    override fun getUserRepository(url: String?): Single<List<GithubUserRepo>> =
        dataSource.getUserRepositories(url)
}