package com.example.cryptoprice.core.presentation.util

import android.content.Context
import com.example.cryptoprice.R
import com.example.cryptoprice.core.domain.util.NetworkError

fun NetworkError.toString(context: Context):String {
  val resId= when(this){
        NetworkError.NO_INTERNET -> R.string.no_internet
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
      NetworkError.UNKNOWN-> R.string.unknown
      NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_request
      NetworkError.SERVER_ERROR -> R.string.server_error
      NetworkError.SERIALIZATION -> R.string.serialization

    }
    return context.getString(resId)
}
