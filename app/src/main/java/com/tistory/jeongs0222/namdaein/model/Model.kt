package com.tistory.jeongs0222.namdaein.model


object Model {

    data class boardItem(val order: Int,
                         val category: Int,
                         val nickname: String,
                         val title: String,
                         val content: String,
                         val date: String,
                         val image0: String,
                         val image1: String,
                         val image2: String,
                         val userkey: String,
                         val nice: String,
                         val dislike: String)

    data class marketItem(val order: Int,
                          val category: Int,
                          val image0: String,
                          val image1: String,
                          val image2: String,
                          val image3: String,
                          val image4: String,
                          val nickname: String,
                          val title: String,
                          val content: String,
                          val date: String,
                          val price: String,
                          val writtenUserkey: String)

    data class commentItem(val nickname: String,
                           val date: String,
                           val content: String)

    data class writtenMarketItem(val order: Int,
                                 val category: Int,
                                 val date: String,
                                 val title: String)

    data class writtenBoardItem(val order: Int,
                                val category: Int,
                                val title: String,
                                val date: String,
                                val content: String)

    data class campusNewsItem(val address: String,
                              val title: String,
                              val image: String)

    data class busTimeTable(val order: Int,
                            val location: String,
                            val boardingGate: String,
                            val price: Int,
                            val school: String,
                            val home: String,
                            val etc: String,
                            val locationImage1: String,
                            val locationImage2: String,
                            val locationImage3: String,
                            val locationImage4: String,
                            val locationImage5: String)

    data class subwayTimeTable(val order: Int,
                               val destination: String,
                               val time: String)
}