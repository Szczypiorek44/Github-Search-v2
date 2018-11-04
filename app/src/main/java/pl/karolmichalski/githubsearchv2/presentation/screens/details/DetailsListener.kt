package pl.karolmichalski.githubsearchv2.presentation.screens.details

interface DetailsListener {
	fun onCardClick(): () -> Unit
}