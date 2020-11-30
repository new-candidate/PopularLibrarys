package ru.geekbrains.githubclient.mvp.view.image

interface IImageLoader<T> {

    fun loadImage(url: String?, container: T)
}