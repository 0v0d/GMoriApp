package com.example.gmoriapp.repository

import com.example.gmoriapp.model.GMoriAPIResponse
import com.example.gmoriapp.service.GMoriAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

interface UserRepository {
    suspend fun getUserList(): Result<List<GMoriAPIResponse>>
}

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class UserRepositoryImpl @Inject constructor(
    private val service: GMoriAPIService
) : UserRepository {
    override suspend fun getUserList(): Result<List<GMoriAPIResponse>> =
        withContext(Dispatchers.IO) {
            return@withContext try {

                val response = service.getUserList()
                if (response.isSuccessful && response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(HttpException(response))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}
