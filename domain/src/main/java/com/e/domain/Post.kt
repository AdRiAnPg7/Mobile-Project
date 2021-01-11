package com.e.domain


data class Post(val postTittle :String? = null,
                val postImage :String? = null,
                val postDate:String? = null,
                val petName:String? = null,
                val petSpecies:String? = null,
                val petRace:String? = null,
                val petColor:String? = null,
                val phoneNumber:String? = null,
                val postDescription:String? = null,
                val postPublisher:String? = "",
                val postUserImage:String? = "",
                val postUserName:String? = "")
