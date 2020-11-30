package ru.geekbrains.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.presenter.UserPresenter
import ru.geekbrains.githubclient.mvp.view.IUserView
import ru.geekbrains.githubclient.mvp.view.image.GlideImageLoader
import ru.geekbrains.githubclient.mvp.view.image.IImageLoader
import ru.geekbrains.githubclient.ui.BackButtonListener
import ru.geekbrains.githubclient.ui.ViewModels.RepositoryViewModel
import ru.geekbrains.githubclient.ui.ViewModels.UserViewModel
import ru.geekbrains.githubclient.ui.adapters.RepositoriesRVAdapter
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), IUserView, BackButtonListener {
    private val imageLoader: IImageLoader<ImageView> = GlideImageLoader()
    private lateinit var userViewModel: UserViewModel
    private lateinit var repositoryViewModel: RepositoryViewModel
    private var mAdapter: RepositoriesRVAdapter? = null

    companion object {
        fun newInstance() = UserFragment()
    }

    val presenter: UserPresenter by moxyPresenter { UserPresenter(GithubApplication.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View.inflate(context, R.layout.fragment_user, null)
    }

    override fun init() {
        mAdapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)

        with(repositories_recycler_view) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun setLogin(login: String?) {
        user_login_text_view.text = login
    }

    override fun loadImage(url: String?) {
        imageLoader.loadImage(url, user_avatar_image_view)
    }

    override fun updateList() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun sendRepository(repository: GithubUserRepo?) {
        repositoryViewModel.getLiveData().postValue(repository)
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel =
            ViewModelProvider(activity!!).get(UserViewModel::class.java)

        repositoryViewModel =
            ViewModelProvider(activity!!).get(RepositoryViewModel::class.java)

        userViewModel.getLiveData().observe(this, {
            presenter.showUserInfo(it)
        })
    }
}