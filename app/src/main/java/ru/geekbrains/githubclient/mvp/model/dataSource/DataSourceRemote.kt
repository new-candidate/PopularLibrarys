package ru.geekbrains.githubclient.mvp.model.dataSource

import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

class DataSourceRemote(private val remoteProvider: RetrofitImpl = RetrofitImpl()) :
    IDataSource {

    override fun getUsers(): Single<List<GithubUser>> =
        remoteProvider.getUsers()

    override fun getUserRepositories(url: String?): Single<List<GithubUserRepo>> =
        remoteProvider.getUserRepositories(url)
}