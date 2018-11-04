package pl.karolmichalski.githubsearchv2.presentation.screens.repos

import pl.karolmichalski.githubsearchv2.data.models.User

interface ReposListener {
	fun onTextChange(charSequence: CharSequence, start: Int, before: Int, count: Int)

	fun onUserClick(): (User) -> Unit

	fun onScroll(): () -> Unit
}