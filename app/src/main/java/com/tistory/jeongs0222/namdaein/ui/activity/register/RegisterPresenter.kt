package com.tistory.jeongs0222.namdaein.ui.activity.register

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import com.tistory.jeongs0222.namdaein.api.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class RegisterPresenter: RegisterContract.Presenter, TextWatcher {

    private lateinit var view: RegisterContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var validate: Boolean = false

    private val apiClient by lazy { ApiClient.create() }

    override fun setView(view: RegisterContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun setUpValidate() {
        if(view.register_nickname().text.isNotEmpty()) {
            disposable = apiClient.nicknameCheck(view.register_nickname().text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {

                    }
                    .doOnError {
                        it.printStackTrace()
                    }
                    .subscribe( {
                        if(it.value == 0) {
                            view.snackBar(it.message)

                            validate = true
                        } else if(it.value == 1) {
                            view.snackBar(it.message)

                            validate = false
                        } else {
                            view.snackBar(it.message)

                            validate = false
                        }
                    })

        }
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        validate = false
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }


}