package ins1der.gifmaker.data.sources.prefs

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ins1der.gifmaker.data.models.api.PlanetAPI

class SharedPrefsDataSource(ctx: Context, moshi: Moshi) {

    private val prefs = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    val planetsAdapter: JsonAdapter<List<PlanetAPI>>

    init {
        val planetsType = Types.newParameterizedType(List::class.java, PlanetAPI::class.java)
        planetsAdapter = moshi.adapter<List<PlanetAPI>>(planetsType)
    }

    var planets: List<PlanetAPI>?
        get() {
            prefs.getString(PLANETS, null)?.let {
                return planetsAdapter.fromJson(it)
            }
            return null
        }
        set(value) {
            prefs.edit().putString(PLANETS, planetsAdapter.toJson(value)).apply()
        }

    companion object {
        private const val PLANETS = "planets"
    }
}