package com.flatwater.ttwwi.fragment.feed

import android.net.Uri
import java.net.URI

data class PostInformation(
    var publisherProfileName : String,
    var publisherProfilePhotoUri : String,
    val publisherDescription : String,
    val publisherUploadImages : List<UploadImages>,
    val userProfilePhoto : Uri
) {
    data class UploadImages(
        val UploadImage : Uri
    )
}

