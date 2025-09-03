package it.datalux.homeworktest.data.photos

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.datalux.homeworktest.data.common.module.NetworkModule
import it.datalux.homeworktest.data.photos.remote.api.PhotosApi
import it.datalux.homeworktest.data.photos.repository.PhotosRepositoryImpl
import it.datalux.homeworktest.domain.PhotosRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object PhotosModule {

    @Singleton
    @Provides
    fun providePhotosApi(retrofit: Retrofit): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }

    @Singleton
    @Provides
    fun providePhotosRepository(dealsApi: PhotosApi): PhotosRepository {
        return PhotosRepositoryImpl(dealsApi)
    }
}