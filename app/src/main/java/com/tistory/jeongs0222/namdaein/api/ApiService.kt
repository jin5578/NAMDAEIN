package com.tistory.jeongs0222.namdaein.api

import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


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
    fun bringBoardComment(@Field("order") order: Int): Observable<boardCommentItems>

    data class boardCommentItems(val comment: MutableList<Model.commentItem>)

    //BoardCommentWriting
    @FormUrlEncoded
    @POST("commentwriting.php")
    fun writingBoardComment(@Field("order") order: Int,
                            @Field("userkey") userkey: String,
                            @Field("content") content: String,
                            @Field("date") date: String): Observable<writeBoardComments>

    data class writeBoardComments(val value: Int, val message: String)   //0: 성공, 1: 실패

    //BoardFavorite
    @FormUrlEncoded
    @POST("nice.php")
    fun writingFavorite(@Field("order") order: Int): Observable<writingFavorites>

    data class writingFavorites(val value: Int, val message: String)   //0: 성공, 1: 실패

    //BoardReport
    @FormUrlEncoded
    @POST("dislike.php")
    fun writingReport(@Field("order") order: Int): Observable<writingReports>

    data class writingReports(val value: Int, val message: String)  //0: 성공, 1: 실패

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
    fun bringMarketComment(@Field("order") order: Int): Observable<marketCommentItems>

    data class marketCommentItems(val comment: MutableList<Model.commentItem>)

    //MarketCommentWriting
    @FormUrlEncoded
    @POST("marketCommentWriting.php")
    fun writingMarketComment(@Field("order") order: Int,
                             @Field("userkey") userkey: String,
                             @Field("content") content: String,
                             @Field("date") date: String): Observable<writeMarketComments>

    data class writeMarketComments(val value: Int, val message: String)  //0: 성공, 1: 실패

    //Nickname Validate
    @FormUrlEncoded
    @POST("nicknamecheck.php")
    fun nicknameCheck(@Field("usernickname") usernickname: String): Observable<nicknameChecks>

    data class nicknameChecks(val value: Int, val message: String)  //0: 사용가능, 1: 이미 존재, 2: 2 ~ 6자

    //Register
    @FormUrlEncoded
    @POST("register.php")
    fun register(@Field("userkey") userkey: String,
                 @Field("usernickname") usernickname: String,
                 @Field("userage") userage: Int,
                 @Field("usermajor") usermajor: String,
                 @Field("usersex") usersex: String,
                 @Field("usertoken") usertoken: String): Observable<registers>

    data class registers(val value: Int, val message: String)   //0: 성공, 1: 실패

    //UserInfo
    @FormUrlEncoded
    @POST("bringNickname.php")
    fun userInfo(@Field("userkey") userkey: String): Observable<userInfos>

    data class userInfos(val value: Int, val nickname: String)

    //WrittenMarketItem
    @FormUrlEncoded
    @POST("writtenMarket.php")
    fun bringWrittenMarket(@Field("userkey") userkey: String): Observable<bringWrittenMarkets>

    data class bringWrittenMarkets(val writtenMarket: MutableList<Model.writtenMarketItem>)

    //WrittenMarketItemDelete
    @FormUrlEncoded
    @POST("deleteMarketItem.php")
    fun deleteWrittenMarket(@Field("order") order: Int): Observable<deleteWrittenMarkets>

    data class deleteWrittenMarkets(val value: Int, val message: String)     //0: 성공, 1: 실패

    //WrittenBeforeMarketItem
    @FormUrlEncoded
    @POST("beforeMarketData.php")
    fun beforeMarketData(@Field("order") order: Int): Observable<Model.marketItem>

    //WrittenAfterMarketItem
    @FormUrlEncoded
    @POST("afterMarketData.php")
    fun afterMarketData(@Field("order") order: Int,
                        @Field("title") title: String,
                        @Field("content") content: String,
                        @Field("price") price: String,
                        @Field("date") date: String): Observable<afterMarketDatas>

    data class afterMarketDatas(val value: Int, val message: String)    //0: 성공, 1: 실패

    //WrittenBoardItem
    @FormUrlEncoded
    @POST("writtenBoard.php")
    fun bringWrittenBoard(@Field("userkey") userkey: String): Observable<bringWrittenBoards>

    data class bringWrittenBoards(val writtenBoard: MutableList<Model.writtenBoardItem>)

    //WrittenBoardItemDelete
    @FormUrlEncoded
    @POST("deleteBoardItem.php")
    fun deleteWrittenBoard(@Field("order") order: Int): Observable<deleteWrittenBoards>

    data class deleteWrittenBoards(val value: Int, val message: String)     //0: 성공, 1: 실패

    //WrittenBeforeBoardItem
    @FormUrlEncoded
    @POST("beforeBoardData.php")
    fun beforeBoardData(@Field("order") order: Int): Observable<Model.boardItem>

    //WrittenAfterBoardItem
    @FormUrlEncoded
    @POST("afterBoardData.php")
    fun afterBoardData(@Field("order") order: Int,
                       @Field("title") title: String,
                       @Field("content") content: String,
                       @Field("date") date: String): Observable<afterBoardDatas>

    data class afterBoardDatas(val value: Int, val message: String)

    //Post Inquire
    @FormUrlEncoded
    @POST("inquire.php")
    fun inquire(@Field("userkey") userkey: String,
                @Field("content") content: String): Observable<inquires>

    data class inquires(val value: Int, val message: String)

    //Nickname Update
    @FormUrlEncoded
    @POST("editInformation.php")
    fun editNickname(@Field("userkey") userkey: String,
                     @Field("usernickname") usernickname: String): Observable<editNicknames>

    data class editNicknames(val value: Int, val message: String)

    //Best Market
    @GET("bestMarket.php")
    fun bestMarket(): Observable<bestMarkets>

    data class bestMarkets(val bestmarket: MutableList<Model.marketItem>)

    //Best Board
    @GET("bestBoard.php")
    fun bestBoard(): Observable<bestBoards>

    data class bestBoards(val bestboard: MutableList<Model.boardItem>)

    //Board Write
    @Multipart
    @POST("marketWriting.php")
    fun marketWrite(@Part up_image: List<MultipartBody.Part>,
                    @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>): Observable<marketWrites>

    data class marketWrites(val value: Int, val message: String)

    //Market Write
    @Multipart
    @POST("boardWriting.php")
    fun boardWrite(@Part up_image: List<MultipartBody.Part>,
                   @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>): Observable<boardWrites>

    data class boardWrites(val value: Int, val message: String)

}