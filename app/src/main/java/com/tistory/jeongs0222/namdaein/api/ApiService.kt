package com.tistory.jeongs0222.namdaein.api

import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    //BoardItem
    @FormUrlEncoded
    @POST("board.php")
    fun bringBoard(@Field("category") category: Int,
                   @Field("category_order_page") pageNumber: Int): Observable<boardItems>

    data class boardItems(val board : MutableList<Model.boardItem>)

    //MarketItem
    @FormUrlEncoded
    @POST("market.php")
    fun bringMarket(@Field("category") category: Int,
                    @Field("category_order_page") pageNumber: Int): Observable<marketItems>

    data class marketItems(val market: MutableList<Model.marketItem>)

}