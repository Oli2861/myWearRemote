package com.oli.mywearremote

import com.oli.mywearremote.network.ActionClient
import com.oli.mywearremote.network.MediaControlsAPI
import com.oli.mywearremote.network.NavigationControlsAPI
import com.oli.mywearremote.network.RetrofitHelper
import com.oli.mywearremote.network.ServerResolver
import com.oli.mywearremote.network.UrlInterceptor
import com.oli.mywearremote.presentation.controls.MediaControlsViewModel
import com.oli.mywearremote.presentation.controls.NavigationControlViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ServerResolver> { ServerResolver() }

    single<UrlInterceptor> { UrlInterceptor(get()) }
    single<RetrofitHelper> { RetrofitHelper(get()) }
    single<MediaControlsAPI> { get<RetrofitHelper>().getMediaControlsAPI(androidContext()) }
    single<NavigationControlsAPI> { get<RetrofitHelper>().getNavigationControlsAPI(androidContext()) }


    single<ActionClient> { ActionClient(get(), get(), get()) }

    viewModel<MediaControlsViewModel>()
    viewModel<NavigationControlViewModel>()

}
