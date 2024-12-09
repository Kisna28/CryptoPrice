package com.example.cryptoprice.di

import com.example.cryptoprice.core.data.networking.HttpClientFactory
import com.example.cryptoprice.crypto.data.networking.RemoteCoinDataSource
import com.example.cryptoprice.crypto.domain.CoinDataSource
import com.example.cryptoprice.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

val appModule = module{
    single {HttpClientFactory.create(CIO.create())}
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)

}