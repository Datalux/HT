package it.datalux.homeworktest.data.photos.remote.api

import it.datalux.homeworktest.data.photos.remote.dto.Photo
import it.datalux.homeworktest.data.photos.remote.dto.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {
    @GET("/photos")
    suspend fun getPhotosList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Photo>>


    @GET("/search/photos")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchResult>

}