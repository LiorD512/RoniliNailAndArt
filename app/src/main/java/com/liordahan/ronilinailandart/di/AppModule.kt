package com.liordahan.ronilinailandart.di

import com.liordahan.ronilinailandart.features.book_appointment.service_and_pricing.repository.ServiceAndPricingRepository
import com.liordahan.ronilinailandart.features.main.home.repository.HomeRepository
import com.liordahan.ronilinailandart.firebase.FirebaseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFirebaseHandler(): FirebaseHandler {
        return FirebaseHandler()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(firebaseHandler: FirebaseHandler): HomeRepository{
        return HomeRepository(firebaseHandler)
    }

    @Provides
    @Singleton
    fun provideServiceAndPricingRepository(firebaseHandler: FirebaseHandler): ServiceAndPricingRepository{
        return ServiceAndPricingRepository(firebaseHandler)
    }

}