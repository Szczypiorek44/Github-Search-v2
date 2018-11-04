package pl.karolmichalski.githubsearchv2.presentation.screens.repos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.githubsearchv2.R
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.data.models.User
import pl.karolmichalski.githubsearchv2.data.models.base.Identified
import pl.karolmichalski.githubsearchv2.presentation.screens.repos.viewholders.RepoViewHolder
import pl.karolmichalski.githubsearchv2.presentation.screens.repos.viewholders.UserViewHolder

@BindingAdapter("reposAndUsers", "onUserClick")
fun RecyclerView.setReposAndUsers(reposAndUsers: List<Identified>, onUserClick: (User) -> Unit) {
	if (adapter == null)
		adapter = RepoAdapter(onUserClick)
	(adapter as RepoAdapter).submitList(reposAndUsers)
}

class RepoAdapter(private val onItemClick: (User) -> Unit) : ListAdapter<Identified, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

	private companion object {
		const val TYPE_REPO = 1
		const val TYPE_USER = 0

		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Identified>() {
			override fun areItemsTheSame(oldItem: Identified, newItem: Identified): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: Identified, newItem: Identified): Boolean {
				return when {
					oldItem is Repo && newItem is Repo -> oldItem.name == newItem.name
					oldItem is User && newItem is User -> oldItem.login == newItem.login
					else -> false
				}
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return if (viewType == TYPE_REPO)
			RepoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false))
		else
			UserViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false), onItemClick)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = getItem(position)
		if (item is Repo)
			(holder as RepoViewHolder).bind(item)
		else if (item is User) {
			(holder as UserViewHolder).bind(item)
		}
	}

	override fun getItemViewType(position: Int): Int {
		return if (getItem(position) is Repo) TYPE_REPO else TYPE_USER
	}
}