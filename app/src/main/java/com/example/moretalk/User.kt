package com.example.moretalk

class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var imageUrl: String? = null
    var bio: String? = null

    constructor(){}

    constructor(name: String?, email: String?, uid: String?, imageUrl: String, bio: String ){
        this.name = name
        this.email = email
        this.uid = uid
        this.imageUrl = imageUrl
        this.bio = bio
    }
}