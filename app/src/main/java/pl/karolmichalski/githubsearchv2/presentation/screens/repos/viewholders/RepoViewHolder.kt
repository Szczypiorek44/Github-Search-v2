package pl.karolmichalski.githubsearchv2.presentation.screens.repos.viewholders

import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.databinding.ItemRepoBinding

class RepoViewHolder(private val binding: ItemRepoBinding)
	: RecyclerView.ViewHolder(binding.root) {

	fun bind(repo: Repo) {
		binding.repo = repo
	}
}