package ru.alekseyk.testskblab.data.db

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

private const val DB_NAME = "db_testskblab"

val databaseModule = module {

    single { createDbInstance(get()) }

}

fun createDbInstance(context: Context): AppDatabase {

    return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .build()
}