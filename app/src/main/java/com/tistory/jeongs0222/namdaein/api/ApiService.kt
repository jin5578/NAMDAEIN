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

    //BoardDetail
    @FormUrlEncoded
    @POST("boardDetail.php")
    fun bringBoardDetail(@Field("order") order: Int): Observable<Model.boardItem>

    //BoardCommentItem
    @FormUrlEncoded
    @POST("boardcomment.php")
    fun bringBoardComment(@Field("order") order: Int):Observable<boardCommentItems>

    data class boardCommentItems(val comment: MutableList<Model.boardCommentItem>)

}