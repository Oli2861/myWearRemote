package com.oli.mywearremote

import com.oli.mywearremote.presentation.controls.MediaControlsViewModel
import com.oli.mywearremote.network.ActionClient
import com.oli.mywearremote.network.MediaControlsAPI
import com.oli.mywearremote.network.NavigationControlsAPI
import com.oli.mywearremote.network.RetrofitHelper
import com.oli.mywearremote.presentation.controls.NavigationControlViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MediaControlsAPI> { RetrofitHelper.getRetrofitInstance(androidContext()).create(MediaControlsAPI::class.java) }
    single<NavigationControlsAPI> { RetrofitHelper.getRetrofitInstance(androidContext()).create(NavigationControlsAPI::class.java) }
    single<ActionClient> { ActionClient(get(), get()) }
    viewModel<MediaControlsViewModel>()
    viewModel<NavigationControlViewModel>()
}
