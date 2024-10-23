package com.example.gmoriapp.module

import com.example.gmoriapp.repository.UserRepository
import com.example.gmoriapp.repository.UserRepositoryImpl
import com.example.gmoriapp.service.GMoriAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideGMoriAPIService(): GMoriAPIService {
        return GMoriAPIService.create()
    }

    @Singleton
    @Provides
    fun provideUserRepository(service: GMoriAPIService): UserRepository {
        return UserRepositoryImpl(service)
    }
}
