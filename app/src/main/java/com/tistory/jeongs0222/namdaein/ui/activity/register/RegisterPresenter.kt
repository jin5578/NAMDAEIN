package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class RegisterPresenter: RegisterContract.Presenter, TextWatcher {

    private lateinit var view: RegisterContract.View
    private lateinit var context: Context

    private var compositeDisposable = CompositeDisposable()

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
            compositeDisposable
                    .add(apiClient.nicknameCheck(view.register_nickname().text.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext {
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
                            .doOnError { it.printStackTrace() }
                            .subscribe()
                    )

        }
    }

    override fun setUpSignIn(connetModel: String) {
        if(validate) {
            val google_uId = FirebaseAuth.getInstance().uid
            val google_token = FirebaseInstanceId.getInstance().token
            val nickname = view.register_nickname().text.toString()

            compositeDisposable
                    .add(apiClient.register(google_uId!!, nickname, 0, "", "", google_token!!)
                            .subscribeOn(Schedulers.io())
                            .doOnNext {
                                if(it.value == 0) {
                                    dbHelper.insert(google_uId, nickname, "on", connetModel)
                                }
                            }
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext {
                                if(it.value == 0) {
                                    view.startActivity(MainActivity::class.java)
                                } else {
                                    view.toastMessage(it.message)

                                    view.confirmClickable(1)
                                }
                            }
                            .doOnError { it.printStackTrace() }
                            .subscribe()
                    )
        } else {
            view.toastMessage("중복체크를 먼저 해주세요.")

            view.confirmClickable(1)
        }
    }

    //TextChange Listener
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        validate = false
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun disposableClear() = compositeDisposable.clear()
}