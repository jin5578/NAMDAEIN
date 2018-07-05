package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.api.ApiClient
import com.tistory.jeongs0222.namdaein.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BoardFreeFragment : Fragment(), BoardFreeContract.View {

    private lateinit var mPresenter: BoardFreePresenter

    private var disposable: Disposable? = null

    private val apiClient by lazy {
        ApiClient.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_board_free_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        mPresenter = BoardFreePresenter()

        mPresenter.setView(this, activity!!)


        disposable = apiClient.bringBoard(0, 0)
                .subscribeOn(Schedulers.io())                                   //Schedulers.io는 네트워크 작업
                .observeOn(AndroidSchedulers.mainThread())                      //AndroidSchedulers.mainThread()는 뷰 작업
                .subscribe(
                        { result -> Log.v("ARTICLES",""+result)},
                        { error -> Log.e("ERROR", error.message) }
                )
    }
}
