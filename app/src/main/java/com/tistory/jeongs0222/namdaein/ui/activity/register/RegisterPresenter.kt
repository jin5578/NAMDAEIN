package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class RegisterPresenter: RegisterContract.Presenter, TextWatcher {

    private lateinit var view: RegisterContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private lateinit var dbHelper: DBHelper

    private var validate: Boolean = false

    private val apiClient by lazy { ApiClient.create() }

    override fun setView(view: RegisterContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpValidate() {
        if(view.register_nickname().text.isNotEmpty()) {
            disposable = apiClient.nicknameCheck(view.register_nickname().text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete { }
                    .doOnError {
                        it.printStackTrace()
                    }
                    .subscribe {
                        if(it.value == 0) {
                            view.toastMessage(it.message)

                            validate = true
                        } else if(it.value == 1) {
                            view.toastMessage(it.message)

                            validate = false
                        } else {
                            view.toastMessage(it.message)

                            validate = false
                        }
                    }

        }
    }

    override fun setUpSignIn(connetModel: String) {
        if(validate) {

            val google_uId = FirebaseAuth.getInstance().uid
            val google_token = FirebaseInstanceId.getInstance().token
            val nickname = view.register_nickname().text.toString()

            Log.e("google_uId", google_uId)
            Log.e("google_token", google_token)
            disposable = apiClient.register(google_uId!!, nickname, 0, "", "", google_token!!)
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        if(it.value == 0) {
                            dbHelper.insert(google_uId, nickname, "on", connetModel)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete { }
                    .doOnError {
                        it.printStackTrace()
                    }
                    .subscribe {
                        if(it.value == 0) {
                            view.startActivity(MainActivity::class.java)
                        } else {
                            view.toastMessage(it.message)
                        }
                    }
        } else {
            view.toastMessage("중복체크를 먼저 해주세요.")
        }
    }



    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        validate = false
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun disposableClear() = disposable!!.dispose()
}