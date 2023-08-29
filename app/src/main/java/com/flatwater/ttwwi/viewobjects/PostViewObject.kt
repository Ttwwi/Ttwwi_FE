package com.flatwater.ttwwi.viewobjects

import android.net.Uri

data class PostViewObject(
    val publisherId : String,
    val publisherNickName : String,
    val publisherProfilePhoto : Uri,
    val photoSet : List<PhotoSet>,
    val postDescription : String,
)

data class PhotoSet(
    val photo : Uri
)
