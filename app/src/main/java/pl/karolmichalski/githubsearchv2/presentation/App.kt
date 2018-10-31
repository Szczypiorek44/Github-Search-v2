package pl.karolmichalski.githubrepos.presentation

import android.app.Application
import pl.karolmichalski.githubrepos.di.AppComponent
import pl.karolmichalski.githubrepos.di.DaggerAppComponent
import pl.karolmichalski.githubrepos.di.ReposModule

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.builder()
				.reposModule(ReposModule(applicationContext))
				.build()
	}
}