package com.example.gmoriapp.view.error

import android.content.Context
import android.net.ParseException
import com.example.gmoriapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getErrorMessage(exception: Exception): String {
        return when (exception) {
            is IOException -> context.getString(R.string.error_io)
            is HttpException -> {
                when (exception.code()) {
                    400 -> context.getString(R.string.error_bad_request)
                    403 -> context.getString(R.string.error_forbidden)
                    404 -> context.getString(R.string.error_not_found)
                    500 -> context.getString(R.string.error_server)
                    else -> context.getString(R.string.error_http, exception.code())
                }
            }
            is ParseException -> context.getString(R.string.error_parse)
            else -> context.getString(R.string.error_unknown)
        }
    }
}
