package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_written_board_fragment.*


class WrittenBoardFragment : Fragment(), WrittenBoardContract.View {

    private lateinit var mPresenter: WrittenBoardPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_written_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = WrittenBoardPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData()
    }

    override fun recyclerView(): RecyclerView = written_board_recyclerView

    override fun emptyTextVisible() {
        written_board_textView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()

        mPresenter.setUpData()
    }

    override fun onDestroyView() {
        mPresenter.disposableClear()

        super.onDestroyView()

    }
}
