package com.tistory.jeongs0222.namdaein.ui.fragment.bustimetable.cheonan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tistory.jeongs0222.namdaein.R
import kotlinx.android.synthetic.main.activity_cheonan_fragment.*

class CheonanFragment : Fragment(), CheonanContract.View {

    private lateinit var mPresenter: CheonanPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_cheonan_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        mPresenter = CheonanPresenter()

        mPresenter.setView(this, activity!!)

        mPresenter.setUpRecyclerView()

        mPresenter.setUpData()
    }

    override fun recyclerView(): RecyclerView {
        return cheonan_recyclerView
    }

    override fun onDestroyView() {
        mPresenter.disposableClear()

        super.onDestroyView()
    }
}
