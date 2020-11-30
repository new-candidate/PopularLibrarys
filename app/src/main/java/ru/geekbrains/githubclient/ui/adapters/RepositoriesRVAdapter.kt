package ru.geekbrains.githubclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.githubclient.mvp.view.itemsView.IRepositoryItemView
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesRVAdapter(
    val presenter: IRepositoryListPresenter
) : RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(
                holder
            )
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        IRepositoryItemView {
        override var pos = -1

        override fun setRepository(repository: String?) {
            with(itemView) {
                repository_text_view.text = repository
            }
        }
    }
}