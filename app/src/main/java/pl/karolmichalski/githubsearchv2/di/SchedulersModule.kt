package pl.karolmichalski.githubsearchv2.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

const val SCHEDULER_MAIN_THREAD = "mainThread"
const val SCHEDULER_IO = "io"

@Module
class SchedulersModule {
	@Provides
	@Named(SCHEDULER_IO)
	fun provideExecutionScheduler(): Scheduler = Schedulers.io()

	@Provides
	@Named(SCHEDULER_MAIN_THREAD)
	fun providePostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()

}