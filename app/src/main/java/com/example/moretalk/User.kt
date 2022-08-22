package com.example.moretalk

class User {
    var name: String? = null
    var userName: String? = null
    var email: String? = null
    var uid: String? = null
    var imageUrl: String? = null
    var bio: String? = null

    constructor(){}

    constructor(name: String?, email: String?, uid: String?, imageUrl: String?, bio: String?, userName: String? ){
        this.name = name
        this.userName = userName
        this.email = email
        this.uid = uid
        this.imageUrl = imageUrl
        this.bio = bio
    }

//    @JvmName("getName1")
//    fun getName(): String?{
//        return name
//    }
//    @JvmName("setName1")
//    fun setName(name: String?){
//        this.name = name
//    }
//
//    @JvmName("getUserName1")
//    fun getUserName(): String?{
//        return userName
//    }
//    @JvmName("setUserName1")
//    fun setUserName(userName: String?){
//        this.userName = userName
//    }
//
//    @JvmName("getImageUrl1")
//    fun getImageUrl(): String?{
//        return imageUrl
//    }
//    @JvmName("setImageUrl1")
//    fun setImageUrl(imageUrl: String?){
//        this.imageUrl = imageUrl
//    }
//
//    @JvmName("getBio1")
//    fun getBio(): String?{
//        return bio
//    }
//    @JvmName("setBio1")
//    fun setBio(bio: String?){
//        this.bio = bio
//    }
}