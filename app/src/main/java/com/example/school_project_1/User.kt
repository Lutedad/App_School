package com.example.school_project_1

data class User(
    var name: String,
    var email: String,
    var diaries: String
){
    constructor():this("","","")
}
