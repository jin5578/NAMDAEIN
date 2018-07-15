package com.tistory.jeongs0222.namdaein.api

import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    //keyCheck
    @FormUrlEncoded
    @POST("keycheck.php")
    fun keyCheck(@Field("userkey") userkey: String): Observable<keyChecks>

    data class keyChecks(val value: Int, val message: String)   //0: 처음 연동, 가입하는 경우 1: 연동은 하였지만 가입을 하지 않은 경우 2: 이미 연동, 가입한 경우

    //BoardItem
    @FormUrlEncoded
    @POST("board.php")
    fun bringBoard(@Field("category") category: Int,
                   @Field("category_order_page") pageNumber: Int): Observable<boardItems>

    data class boardItems(val board : MutableList<Model.boardItem>)

    //BoardDetail
    @FormUrlEncoded
    @POST("boardDetail.php")
    fun bringBoardDetail(@Field("order") order: Int): Observable<Model.boardItem>

    //BoardCommentItem
    @FormUrlEncoded
    @POST("boardcomment.php")
    fun bringBoardComment(@Field("order") order: Int):Observable<boardCommentItems>

    data class boardCommentItems(val comment: MutableList<Model.commentItem>)

    //BoardCommentWriting
    @FormUrlEncoded
    @POST("commentwriting.php")
    fun writingBoardComment(@Field("order") order: Int,
                            @Field("userkey") userkey: String,
                            @Field("content") content: String,
                            @Field("date") date: String) : Observable<writeBoardComment>

    data class writeBoardComment(val value: Int, val message: String)   //0: 성공, 1: 실패

    //BoardFavorite
    @FormUrlEncoded
    @POST("nice.php")
    fun writingFavorite(@Field("order") order: Int) : Observable<writeFavorite>

    data class writeFavorite(val value: Int, val message: String)   //0: 성공, 1: 실패

    //MarketItem
    @FormUrlEncoded
    @POST("market.php")
    fun bringMarket(@Field("category") category: Int,
                    @Field("category_order_page") pageNumber: Int): Observable<marketItems>

    data class marketItems(val market: MutableList<Model.marketItem>)

    //MarketDetail
    @FormUrlEncoded
    @POST("marketDetail.php")
    fun bringMarketDetail(@Field("order") order: Int): Observable<Model.marketItem>

    //MarketCommentItem
    @FormUrlEncoded
    @POST("marketComment.php")
    fun bringMarketComment(@Field("order") order: Int):Observable<marketCommentItems>

    data class marketCommentItems(val comment: MutableList<Model.commentItem>)

    //MarketCommentWriting
    @FormUrlEncoded
    @POST("marketCommentWriting.php")
    fun writingMarketComment(@Field("order") order: Int,
                             @Field("userkey") userkey: String,
                             @Field("content") content: String,
                             @Field("date") date: String) : Observable<writeMarketComment>

    data class writeMarketComment(val value: Int, val message: String)  //0: 성공, 1: 실패

}