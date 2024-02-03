package com.example.school_project_1

data class User(
    var name: String,
    var email: String,
    var uId: String
){
    constructor():this("","","")
}
