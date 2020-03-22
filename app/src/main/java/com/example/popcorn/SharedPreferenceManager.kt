package com.example.popcorn

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceManager private constructor(val sharedPreferences: SharedPreferences) {

    constructor(context: Context, key: String = PREFERENCES_KEY): this(createSharedPreferences(context, key))

    companion object {
        const val PREFERENCES_KEY = "preferences"

        fun createSharedPreferences(context: Context, key: String): SharedPreferences =
            context.getSharedPreferences(key, Context.MODE_PRIVATE)
    }
}

private sealed class PreferenceFieldDelegate<T>(protected val key: () -> kotlin.String, protected val defaultValue: T) :
    ReadWriteProperty<SharedPreferenceManager, T> {

    constructor(key: kotlin.String, defaultValue: T) : this({ key }, defaultValue)

    class Boolean(key: () -> kotlin.String, defaultValue: kotlin.Boolean = false) : PreferenceFieldDelegate<kotlin.Boolean>(key, defaultValue) {

        constructor(key: kotlin.String, defaultValue: kotlin.Boolean = false) : this({ key }, defaultValue)

        override fun getValue(thisRef: SharedPreferenceManager, property: KProperty<*>) =
            thisRef.sharedPreferences.getBoolean(key(), defaultValue)

        override fun setValue(thisRef: SharedPreferenceManager, property: KProperty<*>, value: kotlin.Boolean) =
            thisRef.sharedPreferences.edit().putBoolean(key(), value).apply()
    }

    class Int(key: kotlin.String, defaultValue: kotlin.Int = 0) : PreferenceFieldDelegate<kotlin.Int>(key, defaultValue) {
        override fun getValue(thisRef: SharedPreferenceManager, property: KProperty<*>) =
            thisRef.sharedPreferences.getInt(key(), defaultValue)

        override fun setValue(thisRef: SharedPreferenceManager, property: KProperty<*>, value: kotlin.Int) =
            thisRef.sharedPreferences.edit().putInt(key(), value).apply()
    }

    class String(key: kotlin.String, defaultValue: kotlin.String = "") : PreferenceFieldDelegate<kotlin.String>(key, defaultValue) {
        override fun getValue(thisRef: SharedPreferenceManager, property: KProperty<*>) =
            thisRef.sharedPreferences.getString(key(), defaultValue)
                ?: defaultValue

        override fun setValue(thisRef: SharedPreferenceManager, property: KProperty<*>, value: kotlin.String) =
            thisRef.sharedPreferences.edit().putString(key(), value).apply()
    }

    class Float(key: kotlin.String, defaultValue: kotlin.Float = 0F) : PreferenceFieldDelegate<kotlin.Float>(key, defaultValue) {
        override fun getValue(thisRef: SharedPreferenceManager, property: KProperty<*>) =
            thisRef.sharedPreferences.getFloat(key(), defaultValue)

        override fun setValue(thisRef: SharedPreferenceManager, property: KProperty<*>, value: kotlin.Float) =
            thisRef.sharedPreferences.edit().putFloat(key(), value).apply()
    }

    class Long(key: kotlin.String, defaultValue: kotlin.Long = 0L) : PreferenceFieldDelegate<kotlin.Long>(key, defaultValue) {
        override fun getValue(thisRef: SharedPreferenceManager, property: KProperty<*>) =
            thisRef.sharedPreferences.getLong(key(), defaultValue)

        override fun setValue(thisRef: SharedPreferenceManager, property: KProperty<*>, value: kotlin.Long) =
            thisRef.sharedPreferences.edit().putLong(key(), value).apply()
    }

}
