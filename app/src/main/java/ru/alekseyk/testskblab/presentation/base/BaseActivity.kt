package ru.alekseyk.testskblab.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(
    @LayoutRes private val layoutResource: Int
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        initListeners()
        initViewModelObserving()
    }


    protected abstract fun initListeners()
    protected abstract fun initViews()
    protected abstract fun initViewModelObserving()


}