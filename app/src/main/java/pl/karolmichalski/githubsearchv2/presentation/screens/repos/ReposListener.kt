package pl.karolmichalski.githubsearchv2.presentation.screens.repos

import pl.karolmichalski.githubsearchv2.data.models.Repo

interface ReposListener {
	fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int)

	fun onItemClick(): (Repo) -> Unit
}