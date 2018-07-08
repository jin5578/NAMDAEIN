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
        when(position) {
            0 -> return MarketFemaleFragment()

            1 -> return MarketMaleFragment()

            2 -> return MarketMerchandiseFragment()

            3 -> return MarketBeautyFragment()

            4 -> return MarketBookFragment()

            5 -> return MarketTicketFragment()

            6 -> return MarketApplianceFragment()

            7 -> return MarketLifeFragment()

            8 -> return MarketStudioFragment()

            9 -> return MarketEtcFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

}