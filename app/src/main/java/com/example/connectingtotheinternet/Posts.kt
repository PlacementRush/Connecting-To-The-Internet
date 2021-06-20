package com.example.connectingtotheinternet

import com.squareup.moshi.Json

class Posts {
    @Json(name = "userId")
    var userId: Int? = null
    @Json(name = "id")
    var id: Int? = null
    @Json(name = "title")
    var title: String? = null
    @Json(name = "body")
    var body: String? = null
}
