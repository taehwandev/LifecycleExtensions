package tech.thdev.lifecycle.sample.photo.data.source.search

import io.reactivex.Flowable
import tech.thdev.lifecycle.sample.photo.contract.Contract
import tech.thdev.lifecycle.sample.photo.data.Photo
import tech.thdev.lifecycle.sample.photo.network.PhotoApiService
import tech.thdev.lifecycle.sample.photo.network.createRetrofit

/**
 * Created by tae-hwan on 7/27/17.
 */
object PhotoSearchRemoteDataSource : PhotoSearchDataSource {

    val photoApiService: PhotoApiService by lazy {
        createRetrofit(PhotoApiService::class.java, Contract.PHOTO_SERVER_URL)
    }

    override fun getSearchUser(userKeyword: String, page: Int, perPage: Int): Flowable<List<Photo>> =
            photoApiService.searchUser(userKeyword, page, perPage)
                    .filter {
                        it.photos.photo.isNotEmpty()
                    }
                    .flatMap {
                        Flowable.just(it.photos.photo)
                    }
}