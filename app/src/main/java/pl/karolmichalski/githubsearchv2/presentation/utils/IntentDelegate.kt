package pl.karolmichalski.githubsearchv2.presentation.utils

import android.content.Intent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class IntentDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<Intent, T> {

	class User(key: kotlin.String) : IntentDelegate<pl.karolmichalski.githubsearchv2.data.models.User>(key) {
		override fun getValue(thisRef: Intent, property: KProperty<*>): pl.karolmichalski.githubsearchv2.data.models.User {
			return thisRef.getParcelableExtra(key)
		}

		override fun setValue(thisRef: Intent, property: KProperty<*>, value: pl.karolmichalski.githubsearchv2.data.models.User) {
			thisRef.putExtra(key, value)
		}

	}

}