package com.tistory.jeongs0222.namdaein.model.firebase


class Chat {
    var users: Map<String, Boolean> = HashMap()
    var comments: Map<String, Comment> = HashMap()

    class Comment {
        lateinit var userId: String
        lateinit var message: String
    }
}