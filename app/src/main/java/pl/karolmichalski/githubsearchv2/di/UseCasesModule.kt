package pl.karolmichalski.githubsearchv2.di

import dagger.Module
import dagger.Provides
import pl.karolmichalski.githubsearchv2.domain.interactors.FollowersCountUseCase
import pl.karolmichalski.githubsearchv2.domain.interactors.ReposAndUsersUseCase
import pl.karolmichalski.githubsearchv2.domain.repositories.GithubRepos

@Module
class UseCasesModule {

	@Provides
	fun provideReposAndUsersUseCase(githubRepos: GithubRepos)
			: ReposAndUsersUseCase = ReposAndUsersUseCase(githubRepos)

	@Provides
	fun provideFollowersCountUseCase(githubRepos: GithubRepos)
			: FollowersCountUseCase = FollowersCountUseCase(githubRepos)
}