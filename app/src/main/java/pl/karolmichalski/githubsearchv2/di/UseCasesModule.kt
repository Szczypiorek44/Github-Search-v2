package pl.karolmichalski.githubsearchv2.di

import dagger.Module
import dagger.Provides
import pl.karolmichalski.githubsearchv2.domain.interactors.RepoDetailsUseCase
import pl.karolmichalski.githubsearchv2.domain.interactors.RepoListUseCase
import pl.karolmichalski.githubsearchv2.domain.repositories.GithubRepos

@Module
class UseCasesModule {

	@Provides
	fun provideRepoListUseCase(githubRepos: GithubRepos)
			: RepoListUseCase = RepoListUseCase(githubRepos)

	@Provides
	fun provideRepoDetailsUseCase(githubRepos: GithubRepos)
			: RepoDetailsUseCase = RepoDetailsUseCase(githubRepos)

}