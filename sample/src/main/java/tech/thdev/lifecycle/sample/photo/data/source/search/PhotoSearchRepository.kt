package tech.thdev.lifecycle.sample.photo.data.source.search

import io.reactivex.Flowable
import tech.thdev.lifecycle.sample.photo.data.Photo

/**
 * Created by tae-hwan on 7/27/17.
 */
object PhotoSearchRepository : PhotoSearchDataSource {

    override fun getSearchUser(userKeyword: String, page: Int, perPage: Int): Flowable<List<Photo>>
            = PhotoSearchRemoteDataSource.getSearchUser(userKeyword, page, perPage)
}