package pl.karolmichalski.githubrepos.di

import dagger.Module
import dagger.Provides
import pl.karolmichalski.githubrepos.domain.interactors.RepoDetailsUseCase
import pl.karolmichalski.githubrepos.domain.interactors.RepoListUseCase
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos

@Module
class UseCasesModule {

	@Provides
	fun provideRepoListUseCase(githubRepos: GithubRepos)
			: RepoListUseCase = RepoListUseCase(githubRepos)

	@Provides
	fun provideRepoDetailsUseCase(githubRepos: GithubRepos)
			: RepoDetailsUseCase = RepoDetailsUseCase(githubRepos)

}