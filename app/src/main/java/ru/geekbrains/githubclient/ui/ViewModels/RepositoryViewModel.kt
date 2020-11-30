package ru.geekbrains.githubclient.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo

class RepositoryViewModel : ViewModel() {
    private val liveData = MutableLiveData<GithubUserRepo>()

    fun getLiveData(): MutableLiveData<GithubUserRepo> = liveData
}