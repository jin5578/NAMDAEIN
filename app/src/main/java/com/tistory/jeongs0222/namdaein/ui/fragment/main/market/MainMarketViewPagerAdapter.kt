package com.tistory.jeongs0222.namdaein.ui.fragment.main.market

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.namdaein.ui.fragment.market.appliance.MarketApplianceFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.beauty.MarketBeautyFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.book.MarketBookFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.etc.MarketEtcFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.female.MarketFemaleFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.life.MarketLifeFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.male.MarketMaleFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.merchandise.MarketMerchandiseFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.studio.MarketStudioFragment
import com.tistory.jeongs0222.namdaein.ui.fragment.market.ticket.MarketTicketFragment


class MainMarketViewPagerAdapter(fm: FragmentManager, internal val numOfTabs: Int): FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        return when(position) {
            0 -> MarketFemaleFragment()

            1 -> MarketMaleFragment()

            2 -> MarketMerchandiseFragment()

            3 -> MarketBeautyFragment()

            4 -> MarketBookFragment()

            5 -> MarketTicketFragment()

            6 -> MarketApplianceFragment()

            7 -> MarketLifeFragment()

            8 -> MarketStudioFragment()

            9 -> MarketEtcFragment()

            else -> null
        }
    }

    override fun getCount(): Int = numOfTabs
}