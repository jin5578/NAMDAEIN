package com.tistory.jeongs0222.namdaein.ui.fragment.board.free

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_board_free_fragment.*

class BoardFreeFragment : Fragment(), BoardFreeContract.View {

    private lateinit var mPresenter: BoardFreePresenter

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
        mPresenter.setUpData()
    }

    override fun recyclerView(): RecyclerView {
        return board_free_recyclerView
    }
}
