package com.tistory.jeongs0222.namdaein.utils


object CategorySeparator {

    fun separateMarketCategory(category: String): Int = when (category) {
        "여성의류" -> 0
        "남성의류" -> 1
        "패션잡화" -> 2
        "뷰티" -> 3
        "도서" -> 4
        "티켓" -> 5
        "가전제품" -> 6
        "생활" -> 7
        "원룸" -> 8
        "기타" -> 9
        else -> 10
    }

    fun separateBoardCategory(category: String): Int = when (category) {
        "자유" -> 0
        "분실물" -> 1
        "홍보" -> 2
        "동아리" -> 3
        else -> 4
    }
}