package pl.karolmichalski.githubrepos.domain.interactors

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos
import javax.inject.Inject

class RepoListUseCase @Inject constructor(private val githubRepos: GithubRepos) {

	fun execute(keywords: String?): Single<List<Repo>> = githubRepos.findRepos(keywords)

}