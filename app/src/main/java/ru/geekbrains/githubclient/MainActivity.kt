package ru.geekbrains.githubclient

import android.os.Bundle
import ru.geekbrains.githubclient.mvp.presenter.MainPresenter
import ru.geekbrains.githubclient.mvp.view.IMainView
import ru.geekbrains.githubclient.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), IMainView {
    private val navigatorHolder = GithubApplication.instance.navigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)
    private val presenter: MainPresenter
            by moxyPresenter { MainPresenter(GithubApplication.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backClicked()
    }
}