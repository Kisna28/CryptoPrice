package com.example.cryptoprice.crypto.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd : Double,
    val dateTime : ZonedDateTime
)
