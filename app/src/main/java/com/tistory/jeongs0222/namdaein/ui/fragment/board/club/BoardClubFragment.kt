package com.tistory.jeongs0222.namdaein.ui.fragment.board.club

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_board_club_fragment.*

class BoardClubFragment : Fragment(), BoardClubContract.View {

    private lateinit var mPresenter: BoardClubPresenter

    private val FIRST_LOAD = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_board_club_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("BoardClub_Created", "BoardClub_Created")
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = BoardClubPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setRecyclerView()

        mPresenter.setUpData(FIRST_LOAD)

        mPresenter.loadMore()
    }

    override fun recyclerView(): RecyclerView {
        return board_club_recyclerView
    }

    override fun progressBar(value: Int) {
        when(value) {
            0 -> board_club_progressBar.visibility = View.VISIBLE

            1 -> board_club_progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mPresenter.disposableClear()
    }
}
