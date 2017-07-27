package tech.thdev.lifecycle.sample.photo.data

/**
 * Created by tae-hwan on 7/27/17.
 */
data class Photos(val page: Int,
                  val pages: Int,
                  val perpage: Int,
                  val total: String,
                  val photo: List<Photo>,
                  val stat: String)