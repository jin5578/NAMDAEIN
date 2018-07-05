package com.tistory.jeongs0222.namdaein.model


object Model {

    data class boardItem(val order: Int, val category: Int, val nickname: String, val title: String, val content: String, val date: String, val image0: String, val image1: String, val image2: String, val userkey: String, val nice: String, val dislike: String)
}