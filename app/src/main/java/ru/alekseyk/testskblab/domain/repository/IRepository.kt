package ru.alekseyk.testskblab.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity

interface IRepository {
    fun setUserData(userDbEntity: UserDbEntity): Completable
    fun getCurrentUserData(): Single<List<UserDbEntity>>

    fun getRepositoriesBySearch(query: String): Observable<List<RepositoryDbEntity>>
    fun updateFavoriteStatus(repositoryEntity: RepositoryDbEntity): Completable

    fun getFavoritesRepositories(): Single<List<RepositoryDbEntity>>
    fun deleteUserData(): Completable
}