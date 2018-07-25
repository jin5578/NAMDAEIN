package com.tistory.jeongs0222.namdaein.ui.activity.marketwrite

import android.Manifest
import android.content.Context
import android.net.Uri
import android.support.v4.app.FragmentManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import gun0912.tedbottompicker.TedBottomPicker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MarketWritePresenter: MarketWriteContract.Presenter {

    private lateinit var view: MarketWriteContract.View
    private lateinit var context: Context

    private val spinnerList = arrayOf("여성의류", "남성의류", "패션잡화", "뷰티", "도서", "티켓", "가전제품", "생활", "원룸", "기타")

    private var selectedUriList: MutableList<Uri> = ArrayList()
    private var stringArray: MutableList<String> = ArrayList()
    private var imagess: Array<String>? = null

    private var parts: MutableList<MultipartBody.Part> = ArrayList()

    private lateinit var requestManager: RequestManager


    private var compositeDisposable = CompositeDisposable()

    private var currentDate: String = ""

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: MarketWriteContract.View, context: Context) {
        this.view = view
        this.context = context

        requestManager = Glide.with(context)
    }

    override fun setUpSpinnerFunc() {
        val arrayAdapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, spinnerList)

        view.spinner().setAdapter(arrayAdapter)
    }

    override fun setUpMultiShow(supportFragmentManager: FragmentManager) {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                val bottomSheetDialogFragment = TedBottomPicker.Builder(context)
                        .setImageProvider(object : TedBottomPicker.ImageProvider {
                            override fun onProvideImage(imageView: ImageView?, imageUri: Uri?) {
                                Glide.with(view.selectedLinear())
                                        .load(imageUri)
                                        .into(imageView!!);
                            }

                        })
                        .setOnMultiImageSelectedListener { uriList ->
                            selectedUriList = uriList
                            showUriList(uriList)

                            stringArray = ArrayList<String>()

                            for (i in selectedUriList.indices) {
                                stringArray.add(selectedUriList.get(i).getPath().toString())
                            }

                            imagess = stringArray.toTypedArray()

                            parts = ArrayList<MultipartBody.Part>()

                            for (i in imagess!!.indices) {
                                parts.add(prepareFilePart(imagess!![i]))
                            }
                        }
                        .showCameraTile(false)
                        .setPeekHeight(1200)
                        .setCompleteButtonText("확인")
                        .setEmptySelectionText("NO SELECT")
                        .setPreviewMaxCount(14)
                        .setSelectMaxCount(5)               //최대 선택 가능한 사진 수
                        .setSelectMaxCountErrorText("5개까지 선택 가능합니다.")
                        .setSelectedUriList(selectedUriList as java.util.ArrayList<Uri>?)
                        .create()

                bottomSheetDialogFragment.show(supportFragmentManager)
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                view.toastMessage("permission Denied\n" + deniedPermissions.toString())
            }
        }

        TedPermission(context)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\\n\\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check()
    }

    //BottomPicker
    private fun showUriList(uriList: ArrayList<Uri>) {

            view.selectedLinear().removeAllViews()

            view.selectedLinear().setVisibility(View.VISIBLE)

            val xdpx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, context.resources.getDisplayMetrics()).toInt()
            val hdpx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, context.resources.getDisplayMetrics()).toInt()

            for (uri in uriList) {
                val imageHolder = LayoutInflater.from(context).inflate(R.layout.item_image, null)
                val thumbnail = imageHolder.findViewById(R.id.media_image) as ImageView

                requestManager.load(uri.toString())
                        .apply(RequestOptions().fitCenter())
                        .into(thumbnail)

                view.selectedLinear().addView(imageHolder)

                thumbnail.layoutParams = FrameLayout.LayoutParams(xdpx, hdpx)
            }
    }

    //MultipartBody로 바꿔주는 부분
    private fun prepareFilePart(uri: String): MultipartBody.Part {
        val file = File(uri)
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData("up_image[]", file.name, requestBody)
    }

    override fun setUpBringMarket(order: Int, callback: (String, Model.marketItem) -> Unit) {
        compositeDisposable
                .add(apiClient.beforeMarketData(order)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError {
                            it.printStackTrace()
                        }
                        .subscribe {
                            callback("complete", it)
                            view.spinner().setText(spinnerList.get(it.category))
                        }
                )
    }

    override fun setUpEditConfirmFunc(order: Int) {
        view.progressBar(0)

        bringDate()

        if(view.title().text.isNotEmpty() && view.content().text.isNotEmpty() && view.price().text.isNotEmpty()) {
            compositeDisposable
                    .add(apiClient.afterMarketData(order, view.title().text.toString(), view.content().text.toString(), view.price().text.toString(), currentDate)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                view.viewFinish()
                            }
                            .doOnError {
                                it.printStackTrace()

                                view.progressBar(1)
                            }
                            .subscribe()
                    )
        } else {
            view.toastMessage("빈 칸은 작성할 수 없습니다.")
        }

    }

    override fun setUpConfirmFunc() {

    }

    private fun bringDate() {
        val now = System.currentTimeMillis()

        val date = Date(now)

        val sdf = SimpleDateFormat("yy.MM.dd HH:mm")

        currentDate = sdf.format(date)
    }

    override fun disposableClear() = compositeDisposable.clear()
}