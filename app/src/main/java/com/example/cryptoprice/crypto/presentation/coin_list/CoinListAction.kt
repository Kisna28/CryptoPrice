package com.example.cryptoprice.crypto.presentation.coin_list

import com.example.cryptoprice.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi):CoinListAction
}