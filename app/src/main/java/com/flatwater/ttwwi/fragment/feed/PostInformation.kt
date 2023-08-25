package com.flatwater.ttwwi.fragment.feed

import java.net.URI

data class PostInformation(
    val publisherName : String,
    val publisherPhoto : URI,
    val publisherDescription : String,
    val publisherImages : List<Images>
) {
    data class Images(
        val image : URI
    )
}

