package ru.geekbrains.githubclient.ui.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser

class UserViewModel : ViewModel() {
    private val liveData = MutableLiveData<GithubUser>()

    fun getLiveData(): MutableLiveData<GithubUser> {
        return liveData
    }
}