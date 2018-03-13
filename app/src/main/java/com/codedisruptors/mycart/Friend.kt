package com.codedisruptors.mycart

/**
 * Created by codemagnus on 3/13/18.
 */
class Friend {

    var id: String? = null
    var name: String? = null
    var thumbnailUrl: String? = null

    constructor() {}

    constructor(name: String, thumbnailUrl: String) {
        this.name = name
        this.thumbnailUrl = thumbnailUrl
    }

    constructor(name: String, thumbnailUrl: String, id: String) {
        this.name = name
        this.thumbnailUrl = thumbnailUrl
        this.id = id
    }
}

