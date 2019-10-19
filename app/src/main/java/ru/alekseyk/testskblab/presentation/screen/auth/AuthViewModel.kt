package ru.alekseyk.testskblab.presentation.screen.auth

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.alekseyk.testskblab.domain.usecase.UserUseCase
import ru.alekseyk.testskblab.presentation.base.StateViewModel
import ru.alekseyk.testskblab.presentation.mapper.PresentationMapper
import timber.log.Timber

internal class AuthViewModel(
    private val userUseCase: UserUseCase
) : StateViewModel<AuthViewState>(
    defaultState = AuthViewState()
) {

    fun setUserData(accountName: String) {
        userUseCase.setUserData(PresentationMapper.toUserEntity(accountName, true))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onComplete = {
                    updateState(currentState.copy(isLoading = false, isFinish = true))
                },
                onError = { error ->
                    Timber.e(error)
                    updateState(currentState.copy(isLoading = false))
                }
            ).addTo(disposables)
    }


    fun getCurrentUserData() {
        userUseCase.getCurrentUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { updateState(currentState.copy(isLoading = true)) }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        updateState(currentState.copy(isLoading = false, isFinish = false))
                    } else {
                        updateState(currentState.copy(isLoading = false, isFinish = true))
                    }
                },
                onError = { error ->
                    Timber.e(error)
                    updateState(currentState.copy(isLoading = false))
                }
            ).addTo(disposables)
    }

}