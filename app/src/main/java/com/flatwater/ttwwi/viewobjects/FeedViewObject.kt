package com.flatwater.ttwwi.viewobjects

data class FeedViewObject(
    val trip : EditTripViewObject,
    val postSet : List<Post>
)

data class Post(
    val post : PostViewObject
)
