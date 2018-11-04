package pl.karolmichalski.githubsearchv2.presentation.utils

import android.os.Bundle
import android.os.Parcelable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed class BundleDelegate<T>(protected val key: kotlin.String) : ReadWriteProperty<Bundle, T> {

	class String(key: kotlin.String) : BundleDelegate<kotlin.String>(key) {
		override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.String) {
			thisRef.putString(key, value)
		}

		override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.String {
			return thisRef.getString(key, "")
		}
	}

	class List<K : Parcelable>(key: kotlin.String) : BundleDelegate<kotlin.collections.List<K>>(key) {
		override fun setValue(thisRef: Bundle, property: KProperty<*>, value: kotlin.collections.List<K>) {
			thisRef.putParcelableArrayList(key, ArrayList(value))
		}

		override fun getValue(thisRef: Bundle, property: KProperty<*>): kotlin.collections.List<K> {
			return thisRef.getParcelableArrayList(key) ?: ArrayList()
		}
	}
}