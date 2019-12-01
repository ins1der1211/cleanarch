package ins1der.cleanarch.presentation.di

import com.squareup.moshi.Moshi
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.data.sources.network.PlanetsApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val BASE_PLANETS_URL = "https://swapi.co/api/planets/"
val BASE_PEOPLE_URL = "https://swapi.co/api/people/"

val networkModule = module {

    single { createOkHttpClient() }
    single { createMoshi() }
    single { createMoshiConverterFactory() }

    single(named("planets")) { createPlanetsRetrofit(get(), BASE_PLANETS_URL) }
    single { createPlanetsApiService(get(named("planets"))) }

    single(named("people")) { createPeopleRetrofit(get(), BASE_PEOPLE_URL) }
    single { createPeopleApiService(get(named("people"))) }

    single { createApiDataSource(get(), get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}


fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}

fun createPlanetsRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createPlanetsApiService(retrofit: Retrofit): PlanetsApiService {
    return retrofit.create(PlanetsApiService::class.java)
}

fun createPeopleRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createPeopleApiService(retrofit: Retrofit): PeopleApiService {
    return retrofit.create(PeopleApiService::class.java)
}

fun createApiDataSource(planetsApiService: PlanetsApiService,
                        peopleApiService: PeopleApiService): ApiDataSource {
    return ApiDataSource(planetsApiService, peopleApiService)
}