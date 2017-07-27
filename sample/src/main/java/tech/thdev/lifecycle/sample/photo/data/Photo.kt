package tech.thdev.lifecycle.sample.photo.data

/**
 * Created by tae-hwan on 7/27/17.
 */
data class Photo(val id: String,
                 val owner: String,
                 val secret: String,
                 val server: String,
                 val farm: Int,
                 val title: String,
                 val ispublic: Int,
                 val isfriend: Int,
                 val isfamily: Int,
                 var isLike: Boolean) {

    fun getImageUrl(): String
            = "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"
}