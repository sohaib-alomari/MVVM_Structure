package com.mindorks.bootcamp.learndagger.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.bootcamp.learndagger.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

 abstract class BaseViewModel(
        protected val compositeDisposable: CompositeDisposable,
        protected val networkHelper: NetworkHelper
): ViewModel() {
    val messageStringID=MutableLiveData<Int>()
    val messageString=MutableLiveData<String>()

    protected fun checkInternetkConnection(): Boolean = networkHelper.isNetworkConnected()


    protected fun handleNetworkError(err: Throwable){
        //handle that error
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

     abstract fun onCreate()
}