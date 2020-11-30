package ru.geekbrains.githubclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.githubclient.mvp.view.itemsView.IUserItemView
import ru.geekbrains.githubclient.mvp.view.image.GlideImageLoader
import ru.geekbrains.githubclient.mvp.view.image.IImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*

class UsersRVAdapter(
    val presenter: IUserListPresenter,
    private val imageLoader: IImageLoader<ImageView> = GlideImageLoader()
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(
                holder
            )
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer, IUserItemView {
        override var pos = -1

        override fun setLogin(text: String?) =
            with(containerView) {
                login_text_view.text = text
            }

        override fun loadAvatar(url: String?) {
            with(containerView) {
                imageLoader.loadImage(url, avatar_image_view)
            }
        }
    }
}