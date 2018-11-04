package pl.karolmichalski.githubsearchv2.data.repos

import io.reactivex.Single
import pl.karolmichalski.githubsearchv2.data.models.FindReposResponse
import pl.karolmichalski.githubsearchv2.data.models.FindUsersResponse
import pl.karolmichalski.githubsearchv2.data.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("/search/repositories")
	fun findRepos(@Query("q") keyword: String): Single<FindReposResponse>

	@GET("/search/users")
	fun findUsers(@Query("q") keyword: String): Single<FindUsersResponse>

	@GET("/repos/{owner}/{repo}")
	fun getRepoDetails(@Path("owner") owner: String, @Path("repo") repo: String): Single<Repo>

}