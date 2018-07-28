package com.tistory.jeongs0222.namdaein.ui.fragment.board.lost

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_board_lost_fragment.*


class BoardLostFragment : Fragment(), BoardLostContract.View {

    private lateinit var mPresenter: BoardLostPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_board_lost_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        mPresenter = BoardLostPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData()

        mPresenter.loadMore()
    }

    override fun recyclerView(): RecyclerView = board_lost_recyclerView

    override fun progressBar(value: Int) {
        when(value) {
            0 -> board_lost_progressBar.visibility = View.VISIBLE

            1 -> board_lost_progressBar.visibility = View.GONE
        }
    }

    override fun emptyTextVisible() {
        board_lost_textView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        mPresenter.disposableClear()

        super.onDestroyView()
    }
}
