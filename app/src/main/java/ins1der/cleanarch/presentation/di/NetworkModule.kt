package ins1der.cleanarch.presentation.di

import com.squareup.moshi.Moshi
import ins1der.cleanarch.data.repositories.EmployeesRepositoryImpl
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.data.sources.network.ApiService
import ins1der.cleanarch.domain.repositories.EmployeeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val BASE_URL = "http://dummy.restapiexample.com/api/v1/"

val networkModule = module {

    single { createApiService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }

    single { createMoshiConverterFactory() }

    single { createMoshi() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}


fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}


fun createApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createApiDataSource(apiService: ApiService): ApiDataSource {
    return ApiDataSource(apiService)
}

fun createEmployeeRepository(apiDataSource: ApiDataSource): EmployeeRepository {
    return EmployeesRepositoryImpl(apiDataSource)
}