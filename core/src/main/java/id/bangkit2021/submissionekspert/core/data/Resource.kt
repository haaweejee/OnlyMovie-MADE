package id.bangkit2021.submissionekspert.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : id.bangkit2021.submissionekspert.core.data.Resource<T>(data)
    class Loading<T>(data: T? = null) : id.bangkit2021.submissionekspert.core.data.Resource<T>(data)
    class Error<T>(message: String, data: T? = null) :
        id.bangkit2021.submissionekspert.core.data.Resource<T>(data, message)
}
