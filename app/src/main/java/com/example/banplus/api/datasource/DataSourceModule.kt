package com.example.banplus.api.datasource

import android.content.Context
import androidx.room.Room
import com.example.banplus.URL_BASE
import com.example.banplus.api.ApiServer
import com.example.banplus.db.datasource.DBdataSource
import com.example.banplus.db.schema.CommerceDao
import com.example.banplus.db.schema.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun providerUrl() = URL_BASE

    @Singleton
    @Provides
    fun providerRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): ApiServer =
        retrofit.create(ApiServer::class.java)

    @Singleton
    @Provides
    fun DbDataSource(@ApplicationContext context: Context): DBdataSource {
        return Room.databaseBuilder(context, DBdataSource::class.java, "db_banplus")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun commerceDao(db: DBdataSource): CommerceDao = db.commerceDao()

    @Singleton
    @Provides
    fun transactionDao(db: DBdataSource): TransactionDao = db.transactionDao()


}