package ru.geekbrains.githubclient.mvp.model.entity

import com.google.gson.annotations.Expose

data class GithubUserRepo(
    @Expose val name: String?,
    @Expose val private: Boolean?,
    @Expose val forks: Int?
)