package tech.thdev.lifecycle.sample.photo.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.thdev.lifecycle.sample.photo.BuildConfig
import tech.thdev.lifecycle.sample.photo.data.PhotosContainer

/**
 * Created by tae-hwan on 7/27/17.
 */
interface PhotoApiService {

    // https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=key&format=json&nojsoncallback=1
    @GET("?method=flickr.photos.search&format=json&privacy_filter=1&nojsoncallback=1&safe_search=1&api_key=${BuildConfig.FLICKR_API_KEY}")
    fun searchUser(
            @Query(value = "text", encoded = true) searchKeyword: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int): Flowable<PhotosContainer>
}