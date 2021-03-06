package com.tistory.jeongs0222.namdaein.ui.fragment.written.writtenmarket

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_written_market_fragment.*

class WrittenMarketFragment : Fragment(), WrittenMarketContract.View {

    private lateinit var mPresenter: WrittenMarketPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_written_market_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = WrittenMarketPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData()
    }

    override fun recyclerView(): RecyclerView = written_market_recyclerView

    override fun emptyTextVisible() {
        written_market_textView.visibility = View.VISIBLE
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
