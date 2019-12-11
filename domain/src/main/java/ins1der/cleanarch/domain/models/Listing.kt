package ins1der.cleanarch.domain.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val pageState: LiveData<PageState>,
    val refresh: () -> Unit,
    val retry: () -> Unit)

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class PageState private constructor(
    val status: Status,
    val msg: String? = null) {
    companion object {
        val LOADED =
            PageState(Status.SUCCESS)
        val LOADING =
            PageState(Status.RUNNING)
        fun error(msg: String?) = PageState(
            Status.FAILED,
            msg
        )
    }
}