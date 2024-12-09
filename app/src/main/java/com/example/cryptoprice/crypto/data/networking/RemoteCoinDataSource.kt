package com.example.cryptoprice.crypto.data.networking

import android.util.Log
import com.example.cryptoprice.core.data.networking.constructUrl
import com.example.cryptoprice.core.data.networking.safeCall
import com.example.cryptoprice.core.domain.util.NetworkError
import com.example.cryptoprice.core.domain.util.Result
import com.example.cryptoprice.core.domain.util.map
import com.example.cryptoprice.crypto.data.networking.dto.CoinsHistoryDto
import com.example.cryptoprice.crypto.data.networking.dto.CoinsResponseDto
import com.example.cryptoprice.crypto.data.networking.mappers.toCoin
import com.example.cryptoprice.crypto.data.networking.mappers.toCoinPrice
import com.example.cryptoprice.crypto.domain.Coin
import com.example.cryptoprice.crypto.domain.CoinDataSource
import com.example.cryptoprice.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime


class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map {
                it.toCoin()

            }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinsHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history") ){
                    parameter("interval", "h6")
                    parameter("start", startMillis)
                    parameter("end", endMillis)
                }

        }.map { response ->
            response.data.map {
                it.toCoinPrice()
            }
        }
    }


}