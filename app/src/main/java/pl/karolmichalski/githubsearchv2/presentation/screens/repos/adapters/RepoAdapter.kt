package pl.karolmichalski.githubrepos.presentation.screens.repos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.presentation.screens.repos.viewholders.RepoViewHolder

@BindingAdapter("repoList", "onItemClick")
fun RecyclerView.setRepos(repoList: List<Repo>, onItemClick: (Repo) -> Unit) {
	if (adapter == null)
		adapter = RepoAdapter(onItemClick)
	(adapter as RepoAdapter).submitList(repoList)
}

class RepoAdapter(private val onItemClick: (Repo) -> Unit) : ListAdapter<Repo, RepoViewHolder>(DIFF_CALLBACK) {

	companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
			override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
				return oldItem.name == newItem.name && oldItem.fullName == newItem.fullName
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
		return RepoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false), onItemClick)
	}

	override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}