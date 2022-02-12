package com.mindorks.bootcamp.learndagger.utils

import android.content.Context

import com.mindorks.bootcamp.learndagger.di.ApplicationContext

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(
        // Should be Application Context
        @ApplicationContext private val context: Context) {

    // will check for network connectivity
 fun isNetworkConnected()= false
}
