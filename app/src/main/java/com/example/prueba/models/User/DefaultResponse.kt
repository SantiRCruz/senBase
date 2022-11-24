package com.example.prueba.models.User

data class DefaultResponse(
    var status:String,
    var action:String,
    var show:Boolean,
    var message:String,
    var delay: String,
    var code:String,
    var result:UserResult
)
