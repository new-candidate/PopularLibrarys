package ru.geekbrains.githubclient.mvp.model.api

import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepository(@Url url: String?): Single<List<GithubUserRepo>>
}