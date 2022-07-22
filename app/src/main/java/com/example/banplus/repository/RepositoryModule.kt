package com.example.banplus.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {
    @Singleton
    @Binds
    abstract fun vueltoRepository(repo: VueltoRespositoryImp): VueltoRespository

    @Singleton
    @Binds
    abstract fun reporteRepository(repo: ReportesRespositoryImp): ReportesRespository
}