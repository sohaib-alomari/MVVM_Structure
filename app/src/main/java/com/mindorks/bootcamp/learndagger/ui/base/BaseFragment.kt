package com.mindorks.bootcamp.learndagger.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.mindorks.bootcamp.learndagger.MyApplication
import com.mindorks.bootcamp.learndagger.di.component.ActivityComponent
import com.mindorks.bootcamp.learndagger.di.component.DaggerActivityComponent
import com.mindorks.bootcamp.learndagger.di.component.DaggerFragmentComponent
import com.mindorks.bootcamp.learndagger.di.component.FragmentComponent
import com.mindorks.bootcamp.learndagger.di.module.ActivityModule
import com.mindorks.bootcamp.learndagger.di.module.FragmentModule
import javax.inject.Inject

abstract class BaseFragment <VM: BaseViewModel> : Fragment(){


    @Inject
    lateinit var viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.onCreate()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =inflater.inflate(provideLayoutId(),container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    protected open fun setupObservers()  {
        viewModel.messageStringID.observe(this, androidx.lifecycle.Observer {  })
        viewModel.messageString.observe(this, androidx.lifecycle.Observer {  })
    }

    private fun buildFragmentComponent() =
        DaggerFragmentComponent
            .builder()
            .applicationComponent((context!!.applicationContext as MyApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    fun showMessage(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


    @LayoutRes
    protected abstract fun provideLayoutId() :Int

    protected abstract fun setupView(view : View)

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)
}