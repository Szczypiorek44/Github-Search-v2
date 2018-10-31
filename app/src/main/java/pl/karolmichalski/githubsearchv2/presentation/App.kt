package pl.karolmichalski.githubsearchv2.presentation

import android.app.Application
import pl.karolmichalski.githubsearchv2.di.AppComponent
import pl.karolmichalski.githubsearchv2.di.DaggerAppComponent
import pl.karolmichalski.githubsearchv2.di.ReposModule

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.builder()
				.reposModule(ReposModule(applicationContext))
				.build()
	}
}