package com.example.cryptoprice.crypto.presentation.coin_list

import com.example.cryptoprice.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}