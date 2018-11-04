package pl.karolmichalski.githubsearchv2.domain.interactors

import io.reactivex.Single
import pl.karolmichalski.githubsearchv2.data.models.base.Identified
import pl.karolmichalski.githubsearchv2.domain.repositories.GithubRepos
import javax.inject.Inject

class ReposAndUsersUseCase @Inject constructor(private val githubRepos: GithubRepos) {

	fun execute(keywords: String?): Single<List<Identified>> = githubRepos.findReposAndUsers(keywords)

}