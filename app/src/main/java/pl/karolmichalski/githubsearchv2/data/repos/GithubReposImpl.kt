package pl.karolmichalski.githubrepos.data.repos

import android.content.Context
import io.reactivex.Single
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.data.exceptions.BlankInputException
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos

class GithubReposImpl(private val context: Context,
					  private val apiService: ApiService) : GithubRepos {

	override fun findRepos(keywords: String?): Single<List<Repo>> {
		return when {
			keywords.isNullOrBlank() -> Single.fromCallable { throw BlankInputException(context.getString(R.string.enter_keywords)) }
			else -> apiService.findRepos(keywords!!).flatMap { Single.just(it.repoList) }
		}
	}

	override fun getRepoDetails(owner: String, repo: String): Single<Repo> {
		return apiService.getRepoDetails(owner, repo)
	}

}