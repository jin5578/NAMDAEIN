package com.tistory.jeongs0222.namdaein.ui.fragment.market.merchandise

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_market_merchandise_fragment.*


class MarketMerchandiseFragment : Fragment(), MarketMerchandiseContract.View {

    private lateinit var mPresenter: MarketMerchandisePresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_market_merchandise_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = MarketMerchandisePresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData()

        mPresenter.loadMore()
    }

    override fun recyclerView(): RecyclerView = market_merchandise_recyclerView

    override fun progressBar(value: Int) {
        when(value) {
            0 -> market_merchandise_progressBar.visibility = View.VISIBLE

            1 -> market_merchandise_progressBar.visibility = View.GONE
        }
    }

    override fun emptyTextVisible() {
        market_merchandise_textView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        mPresenter.disposableClear()

        super.onDestroyView()
    }
}
