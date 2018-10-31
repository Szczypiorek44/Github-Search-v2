package pl.karolmichalski.githubsearchv2.presentation.screens.repos

import pl.karolmichalski.githubsearchv2.data.models.Repo

interface ReposListener {
	fun onSearchClick()

	fun onItemClick(): (Repo) -> Unit
}