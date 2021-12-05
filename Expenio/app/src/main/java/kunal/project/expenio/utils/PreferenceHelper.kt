package kunal.project.expenio.utils

import android.content.Context
import android.content.SharedPreferences


object PreferenceHelper {
    private const val SHARED_PREFERENCE_KEY = "kunal.project.expenio.utils"

    fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    fun writeIntToPreference(context: Context, key: String?, value: Int) {
        val editor = getSharedPreference(context).edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun writeStringToPreference(context: Context, key: String?, value: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringFromPreference(context: Context, key: String?): String? {
        return getSharedPreference(context).getString(key, null)
    }

    fun getIntFromPreference(context: Context, key: String?): Int {
        return getSharedPreference(context).getInt(key, 0)
    }
}