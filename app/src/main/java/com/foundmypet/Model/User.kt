package com.foundmypet.Model

class User {
    private var email: String = ""
    private var image: String = ""
    private var uid: String = ""
    private var user: String = ""
    private var cellphone: String = ""
    private var city : String = ""
    private var country : String = ""

    constructor()

    constructor(user:String,image:String, email:String, uid:String){
        this.user = user
        this.image = image
        this.email = email
        this.uid = uid
    }

    fun getUser(): String{
        return user
    }

    fun setUser(newUser:String){
        this.user = newUser
    }

    fun getEmail(): String{
        return email
    }
    fun setEmail(newEmail:String){
        this.email = newEmail
    }

    fun getImage(): String{
        return image
    }

    fun setImage(newImage:String){
        this.image = newImage
    }

    fun getUid(): String{
        return uid
    }

    fun setUid(newUid:String){
        this.uid = newUid
    }

    fun getCellphone(): String{
        return cellphone
    }
    fun getCountry(): String{
        return country
    }
    fun getCity(): String{
        return city
    }
}