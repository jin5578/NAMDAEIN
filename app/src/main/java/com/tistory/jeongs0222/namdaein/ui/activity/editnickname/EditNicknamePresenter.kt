package com.tistory.jeongs0222.namdaein.ui.activity.editnickname

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.DBHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class EditNicknamePresenter: EditNicknameContract.Presenter, TextWatcher {

    private lateinit var view: EditNicknameContract.View
    private lateinit var context: Context

    private var disposable: Disposable? = null

    private var validate: Boolean = false

    private lateinit var dbHelper: DBHelper

    private val apiClient by lazy { ApiClient.create() }


    override fun setView(view: EditNicknameContract.View, context: Context) {
        this.view = view
        this.context = context

        dbHelper = DBHelper(context, "USERINFO.db", null, 1)
    }

    override fun setUpNicknameFunc() {
        view.edit_nickname().addTextChangedListener(this@EditNicknamePresenter)
    }

    override fun setUpValidate() {
        if(view.edit_nickname().text.isNotEmpty()) {
            disposable = apiClient.nicknameCheck(view.edit_nickname().text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { it.printStackTrace() }
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

    override fun setUpNicknameUpdate() {
        if(validate) {
            disposable = apiClient.editNickname(dbHelper.getGoogle_uId()!!, view.edit_nickname().text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        if(it.value == 0) {
                            dbHelper.nicknameUpdate(view.edit_nickname().text.toString())
                        } else {
                            view.toastMessage(it.message)
                        }
                    }
                    .doOnError { it.printStackTrace() }
                    .subscribe{
                        view.viewFinish()
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