package pl.karolmichalski.githubsearchv2.presentation.screens.repos.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.githubsearchv2.data.models.User
import pl.karolmichalski.githubsearchv2.databinding.ItemUserBinding

class UserViewHolder(private val binding: ItemUserBinding,
					 private val onUserClick: (User) -> Unit)
	: RecyclerView.ViewHolder(binding.root), View.OnClickListener {

	init {
		binding.listener = this
	}

	override fun onClick(v: View?) {
		binding.user?.apply {
			onUserClick(this)
		}
	}

	fun bind(user: User) {
		binding.user = user
	}
}