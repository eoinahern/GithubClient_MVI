package ie.eoinahern.githubclient.ui.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ie.eoinahern.githubclient.R
import ie.eoinahern.githubclient.data.model.RepoItem
import javax.inject.Inject


class ReposActivityAdapter @Inject constructor() :
    RecyclerView.Adapter<ReposActivityAdapter.ViewHolder>() {

    private val itemList: MutableList<RepoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.repos_single_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateAdapter(listIn: List<RepoItem>) {
        if (itemList.isEmpty()) {
            itemList.addAll(listIn)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameText = itemView.findViewById<TextView>(R.id.name)
        private val languageText = itemView.findViewById<TextView>(R.id.language)

        fun bind(repo: RepoItem) {
            nameText.text = repo.name
            languageText.text = repo.language ?: itemView.context.getString(R.string.unknown_txt)
        }
    }
}