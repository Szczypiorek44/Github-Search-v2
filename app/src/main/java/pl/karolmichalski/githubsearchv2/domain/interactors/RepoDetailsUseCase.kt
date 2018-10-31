package pl.karolmichalski.githubsearchv2.domain.interactors

import io.reactivex.Single
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.domain.repositories.GithubRepos
import javax.inject.Inject

class RepoDetailsUseCase @Inject constructor(private val githubRepos: GithubRepos) {

	fun execute(owner: String, repo: String): Single<Repo> = githubRepos.getRepoDetails(owner, repo)

}