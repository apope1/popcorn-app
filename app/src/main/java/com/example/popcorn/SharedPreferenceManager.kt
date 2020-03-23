package com.example.popcorn

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceManager private constructor(val sharedPreferences: SharedPreferences) {

    constructor(context: Context, key: String = PREFERENCES_KEY) : this(createSharedPreferences(context, key))

    var suggestions by PreferenceFieldDelegate.Set(SUGGESTIONS_KEY)

    companion object {
        const val PREFERENCES_KEY = "preferences"
        const val SUGGESTIONS_KEY = "suggestions"

        fun createSharedPreferences(context: Context, key: String): SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
    }
}

private sealed class PreferenceFieldDelegate<T>(protected val key: () -> String, protected val defaultValue: T) :
    ReadWriteProperty<SharedPreferenceManager, T> {

    constructor(key: String, defaultValue: T) : this({ key }, defaultValue)

    class Set(key: String, defaultValue: kotlin.collections.Set<String> = emptySet()) :
        PreferenceFieldDelegate<kotlin.collections.Set<String>>(key, defaultValue) {
        override fun getValue(thisRef: SharedPreferenceManager, property: KProperty<*>) =
            thisRef.sharedPreferences.getStringSet(key(), defaultValue)?.toSet() ?: defaultValue

        override fun setValue(thisRef: SharedPreferenceManager, property: KProperty<*>, value: kotlin.collections.Set<String>) =
            thisRef.sharedPreferences.edit().putStringSet(key(), value).apply()
    }
}
