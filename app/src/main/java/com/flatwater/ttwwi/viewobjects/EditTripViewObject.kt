package com.flatwater.ttwwi.viewobjects

import android.net.Uri

data class EditTripViewObject(
    val tripTitle : String,
    val tripProfilePhoto : Uri,
    val tripSubTitle : String,
    val tripDescription : String,
    val tripPassword : String,
)
