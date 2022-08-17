package com.mascotasol.di

import com.google.firebase.firestore.FirebaseFirestore
import com.mascotasol.data.repository.FirebaseRepository
import com.mascotasol.data.repository.FirebaseRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        firebaseDatabase: FirebaseFirestore
    ): FirebaseRepository {
        return FirebaseRepositoryImp(firebaseDatabase)
    }
}