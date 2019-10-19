package ru.alekseyk.testskblab.data.datasource.remote

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.alekseyk.testskblab.data.datasource.IDataSource
import ru.alekseyk.testskblab.data.db.entity.RepositoryDbEntity
import ru.alekseyk.testskblab.data.db.entity.UserDbEntity
import ru.alekseyk.testskblab.data.network.Api


class RemoteDataSource(private val api: Api) : IDataSource {

    override fun getRepositoriesBySearch(): Observable<List<RepositoryDbEntity>> {
        return Observable.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun getCurrentUserData(): Single<List<UserDbEntity>> {
        return Single.error(Exception("Method only for LocalDataSource realization"))
    }

    override fun setUserData(userDbEntity: UserDbEntity): Completable {
        return Completable.error(Exception("Method only for LocalDataSource realization"))
    }


}