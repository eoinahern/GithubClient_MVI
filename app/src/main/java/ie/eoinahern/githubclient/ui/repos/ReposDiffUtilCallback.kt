package ie.eoinahern.githubclient.ui.repos

import androidx.recyclerview.widget.DiffUtil
import ie.eoinahern.githubclient.data.model.RepoItem
import javax.inject.Inject


class ReposDiffUtilCallback @Inject constructor() : DiffUtil.ItemCallback<RepoItem>() {

    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        TODO("Not yet implemented")
    }


}