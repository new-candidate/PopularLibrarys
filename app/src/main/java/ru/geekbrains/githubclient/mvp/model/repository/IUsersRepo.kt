package ru.geekbrains.githubclient.mvp.model.repository

import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepository(url: String?): Single<List<GithubUserRepo>>
}