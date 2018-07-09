package com.tistory.jeongs0222.namdaein.ui.fragment.market.book

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_market_book_fragment.*

class MarketBookFragment : Fragment(), MarketBookContract.View {

    private lateinit var mPresenter: MarketBookPresenter

    private val FIRST_LOAD = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_market_book_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = MarketBookPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData(FIRST_LOAD)

        mPresenter.loadMore()
    }

    override fun recyclerView(): RecyclerView {
        return market_book_recyclerView
    }

    override fun progressBar(value: Int) {
        when(value) {
            0 -> market_book_progressBar.visibility = View.VISIBLE

            1 -> market_book_progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mPresenter.disposableClear()
    }
}
