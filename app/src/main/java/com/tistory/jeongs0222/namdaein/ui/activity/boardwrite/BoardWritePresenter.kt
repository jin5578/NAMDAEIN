package com.tistory.jeongs0222.namdaein.ui.activity.boardwrite

import android.Manifest
import android.R
import android.content.Context
import android.net.Uri
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gun0912.tedpermission.TedPermission
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.model.Model
import com.tistory.jeongs0222.namdaein.utils.*
import gun0912.tedbottompicker.TedBottomPicker
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class BoardWritePresenter: BoardWriteContract.Presenter, PermissionCallbackListener {

    private lateinit var view: BoardWriteContract.View
    private lateinit var context: Context

    //Image Url
    private var selectedUriList: MutableList<Uri> = ArrayList()
    private var stringArray: MutableList<String> = ArrayList()
    private var imagess: Array<String>? = null

    private var parts: MutableList<MultipartBody.Part> = ArrayList()

    private lateinit var requestManager: RequestManager

    private var compositeDisposable = CompositeDisposable()

    private var category: Int = 4

    private lateinit var dbHelper: DBHelper

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: BoardWriteContract.View, context: Context) {
        this.view = view
        this.context = context

        requestManager = Glide.with(context)

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpSpinnerFunc() {
        val arrayAdapter = ArrayAdapter<String>(context, R.layout.simple_dropdown_item_1line, ArrayUtil.boardSpinnerList)

        view.spinner().setAdapter(arrayAdapter)
    }

    //BottomPicker
    override fun setUpMultiShow(supportFragmentManager: FragmentManager) =
            TedPermission(context)
                    .setPermissionListener(PermissionUtil(this, supportFragmentManager))
                    .setDeniedMessage("If you reject permission,you can not use this service\\n\\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .check()

    //BottomPicker
    override fun onSuccess(supportFragmentManager: FragmentManager) {
        TedBottomPicker.Builder(context)
                .setImageProvider { imageView, imageUri -> setBottomPickerImage(imageView, imageUri) }
                .setOnMultiImageSelectedListener { uriList -> setMultiImageSelected(uriList) }
                .showCameraTile(false)
                .setPeekHeight(1200)
                .setCompleteButtonText("확인")
                .setEmptySelectionText("NO SELECT")
                .setPreviewMaxCount(14)
                .setSelectMaxCount(5)               //최대 선택 가능한 사진 수
                .setSelectMaxCountErrorText("5개까지 선택 가능합니다.")
                .setSelectedUriList(selectedUriList as java.util.ArrayList<Uri>?)
                .create()
                .show(supportFragmentManager)

    }

    //BottomPicker
    override fun onFail(deniedPermissions: ArrayList<String>?) {
        view.toastMessage("permission Denied\n" + deniedPermissions.toString())
    }

    override fun setUpBringBoard(sort: Int, order: Int, callback: (Model.boardItem) -> Unit) {

        if(sort == 1) {
            view.writeImageConstraint().visibility = View.GONE
            compositeDisposable
                    .add(apiClient.beforeBoardData(order)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError { it.printStackTrace() }
                            .subscribe { callback(it) }
                    )
        }
    }

    override fun setUpEditConfirmFunc(order: Int) {

        view.progressBar(0)

        if(view.title().text.isNotEmpty() && view.content().text.isNotEmpty()) {
            compositeDisposable.add(
                    apiClient.afterBoardData(order, view.title().text.toString(), view.content().text.toString(), DateUtil.bringDate())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                view.progressBar(1)

                                view.viewFinish()
                            }
                            .doOnError { it.printStackTrace() }
                            .subscribe()
            )
        } else {
            view.progressBar(1)

            view.toastMessage("빈 칸은 작성할 수 없습니다.")

            view.confirmClickable(0)
        }
    }

    override fun setUpConfirmFunc() {
        category = CategorySeparator.separateBoardCategory(view.spinner().text.toString())

        if(view.title().text.isNotEmpty() && view.content().text.isNotEmpty() && category != 4) {
            view.progressBar(0)

            compositeDisposable.add(apiClient.boardWrite(parts, getBoardWriteItem())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        view.progressBar(1)
                        view.viewFinish()
                    }
                    .doOnError { it.printStackTrace() }
                    .subscribe()
            )
        } else {
            view.toastMessage("빈 칸은 작성할 수 없습니다.")

            view.confirmClickable(0)
        }

    }

    //BottomPicker
    private fun setBottomPickerImage(imageView: ImageView, uri: Uri) =
            Glide.with(view.selectedLinear())
                    .load(uri)
                    .apply(RequestOptions().centerCrop())
                    .into(imageView)

    //BottomPicker
    private fun setMultiImageSelected(arr: ArrayList<Uri>) {
        selectedUriList = arr

        showUriList(arr)

        stringArray = ArrayList<String>()

        for(i in selectedUriList.indices) {
            stringArray.add(selectedUriList[i].path.toString())
        }

        imagess = stringArray.toTypedArray()

        parts = ArrayList<MultipartBody.Part>()

        for(i in imagess!!.indices) {
            parts.add(prepareFilePart(imagess!![i]))
        }
    }

    //BottomPicker
    private fun showUriList(uriList: ArrayList<Uri>) {
        showListVisibility()

        Observable.fromIterable(uriList)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    DynamicViewUtil.showUri(it, context, requestManager) { view.selectedLinear().addView(it) }
                }
                .subscribe()
    }

    //BottomPicker
    private fun showListVisibility() {
        view.selectedLinear().removeAllViews()
        view.selectedLinear().visibility = View.VISIBLE
    }

    //MultipartBody로 바꿔주는 부분
    private fun prepareFilePart(uri: String): MultipartBody.Part {
        val file = File(uri)
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData("up_image[]", file.name, requestBody)
    }

    private fun getBoardWriteItem(): HashMap<String, RequestBody> {
        return HashMap<String, RequestBody>().apply {
            this["category"] = toRequestBody(category.toString())
            this["userkey"] = toRequestBody(dbHelper.getGoogle_uId()!!)
            this["date"] = toRequestBody(DateUtil.bringDate())
            this["title"] = toRequestBody(view.title().text.toString())
            this["content"] = toRequestBody(view.content().text.toString())
        }
    }

    private fun toRequestBody(value: String): RequestBody =
            RequestBody.create(MediaType.parse("text/plain"), value)

    override fun disposableClear() = compositeDisposable.clear()
}