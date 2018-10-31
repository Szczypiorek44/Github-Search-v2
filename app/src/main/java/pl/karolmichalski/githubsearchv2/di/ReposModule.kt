package pl.karolmichalski.githubrepos.di

import android.content.Context
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import pl.karolmichalski.githubrepos.BuildConfig
import pl.karolmichalski.githubrepos.data.repos.ApiService
import pl.karolmichalski.githubrepos.data.repos.GithubReposImpl
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton


private const val API_URL = "https://api.github.com/"

@Module
class ReposModule(private val context: Context) {

	@Provides
	@Singleton
	fun provideGithubRepos(apiService: ApiService): GithubRepos {
		return GithubReposImpl(context, apiService)
	}

	@Provides
	@Singleton
	fun provideApiService(): ApiService {
		val loggingInterceptor = HttpLoggingInterceptor().apply {
			level = if (BuildConfig.DEBUG) BODY else NONE
		}

		val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(loggingInterceptor)
				.build()

		val objectMapper = ObjectMapper()
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

		val retrofit = Retrofit.Builder()
				.baseUrl(API_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.addConverterFactory(JacksonConverterFactory.create(objectMapper))
				.build()

		return retrofit.create(ApiService::class.java)
	}
}