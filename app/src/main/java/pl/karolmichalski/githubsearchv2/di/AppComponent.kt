package pl.karolmichalski.githubrepos.di

import dagger.Component
import pl.karolmichalski.githubrepos.presentation.screens.details.DetailsViewModel
import pl.karolmichalski.githubrepos.presentation.screens.repos.ReposViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ReposModule::class, UseCasesModule::class, SchedulersModule::class])
interface AppComponent {
	fun inject(reposViewModel: ReposViewModel)
	fun inject(detailsViewModel: DetailsViewModel)
}