package pl.karolmichalski.githubrepos.domain.interactors

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos
import javax.inject.Inject

class RepoDetailsUseCase @Inject constructor(private val githubRepos: GithubRepos) {

	fun execute(owner: String, repo: String): Single<Repo> = githubRepos.getRepoDetails(owner, repo)

}