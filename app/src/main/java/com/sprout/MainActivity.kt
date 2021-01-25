package com.sprout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityMainBinding
import com.sprout.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
) {
    override fun initView() {

        var list: List<Fragment> = mViewModel.fragment

        vp_main!!.adapter = ViewPage(supportFragmentManager, list)

        tab_main.setupWithViewPager(vp_main)
        for (i in list.indices) {
            tab_main.getTabAt(i)?.setIcon(mViewModel.icon[i])
        }

    }

    class ViewPage(fragmentManager: FragmentManager, val list: List<Fragment>) :
        FragmentStatePagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

    }

    override fun initData() {

    }

    override fun initVariable() {
    }

    override fun initVM() {
    }

}