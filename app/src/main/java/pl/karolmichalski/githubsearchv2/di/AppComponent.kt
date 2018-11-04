package pl.karolmichalski.githubsearchv2.di

import dagger.Component
import pl.karolmichalski.githubsearchv2.presentation.screens.details.DetailsViewModel
import pl.karolmichalski.githubsearchv2.presentation.screens.repos.ReposViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ReposModule::class, UseCasesModule::class])
interface AppComponent {
	fun inject(reposViewModel: ReposViewModel)
	fun inject(detailsViewModel: DetailsViewModel)
}