package ru.alekseyk.testskblab.presentation.screen.main

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule= module {
    viewModel { MainViewModel() }
}