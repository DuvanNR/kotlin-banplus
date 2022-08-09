package com.example.banplus.api.datasource

import android.content.Context
import androidx.room.Room
import com.example.banplus.URL_BASE
import com.example.banplus.URL_BASE_BACKEND
import com.example.banplus.api.ApiServer
import com.example.banplus.api.ApiServerBackend
import com.example.banplus.api.ApiServiceInterceptor
import com.example.banplus.db.datasource.DBdataSource
import com.example.banplus.db.schema.CommerceDao
import com.example.banplus.db.schema.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
private val okHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(ApiServiceInterceptor)
    .build()
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun providerUrl() = URL_BASE
    @Singleton
    @Provides
    @Named("BaseUrlBackend")
    fun providerUrlBackend() = URL_BASE_BACKEND

    @Singleton
    @Provides
    fun BancoRetrofit(@Named("BaseUrl") baseUrl: String): ApiServer {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServer::class.java)
    }


    @Singleton
    @Provides
    fun BackendRetrofit(@Named("BaseUrlBackend") baseUrl: String): ApiServerBackend {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServerBackend::class.java)
    }




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