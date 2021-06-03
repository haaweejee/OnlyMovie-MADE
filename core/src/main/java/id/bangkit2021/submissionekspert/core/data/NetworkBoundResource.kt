package id.bangkit2021.submissionekspert.core.data

import id.bangkit2021.submissionekspert.core.data.remote.api.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<id.bangkit2021.submissionekspert.core.data.Resource<ResultType>> =
        flow {
            emit(id.bangkit2021.submissionekspert.core.data.Resource.Loading())
            val dbSource = loadFromDb().first()
            if (shouldFetch(dbSource)) {
                emit(id.bangkit2021.submissionekspert.core.data.Resource.Loading())
                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDb().map {
                            id.bangkit2021.submissionekspert.core.data.Resource.Success(
                                it
                            )
                        })
                    }
                    is ApiResponse.Empty -> {
                        emitAll(loadFromDb().map {
                            id.bangkit2021.submissionekspert.core.data.Resource.Success(
                                it
                            )
                        })
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(
                            id.bangkit2021.submissionekspert.core.data.Resource.Error<ResultType>(
                                apiResponse.errorMessage
                            )
                        )
                    }
                }
            } else {
                emitAll(loadFromDb().map {
                    id.bangkit2021.submissionekspert.core.data.Resource.Success(
                        it
                    )
                })
            }
        }


    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)


    fun asFlow(): Flow<id.bangkit2021.submissionekspert.core.data.Resource<ResultType>> = result
}