package ins1der.gifmaker.data.utils

import retrofit2.Response
import java.lang.Exception

fun <T> Response<T>.successBody(): T {
    return body()!!
}

fun <T> Response<T>.error(): Exception {
    return Exception(errorBody().toString())
}