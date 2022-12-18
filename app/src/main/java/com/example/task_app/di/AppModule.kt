package com.example.task_app.di

import android.content.Context
import androidx.room.Room
import com.example.task_app.data.repository.Repository
import com.example.task_app.data.network.Api
import com.example.task_app.data.local.room.dao.ToSellDao
import com.example.task_app.data.local.room.database.ToSellDatabase
import com.example.task_app.utils.Constant.BASE_URL
import com.example.task_app.utils.Constant.DATABASE_NAME
import com.example.task_app.utils.Constant.PRELOADED_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideRepository(@ApplicationContext context: Context, api: Api, toSellDao: ToSellDao): Repository {
        return Repository(context, api,toSellDao)
    }
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context)= Room.databaseBuilder(context,
        ToSellDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset(PRELOADED_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: ToSellDatabase): ToSellDao {
        return database.toSellDao()
    }
}