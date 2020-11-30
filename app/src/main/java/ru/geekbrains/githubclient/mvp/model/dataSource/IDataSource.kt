package ru.geekbrains.githubclient.mvp.model.dataSource

import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IDataSource {

    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepositories(url: String?): Single<List<GithubUserRepo>>
}