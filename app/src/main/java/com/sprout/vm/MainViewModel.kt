package com.sprout.vm

import androidx.fragment.app.Fragment
import com.sprout.R
import com.sprout.base.BaseViewModel
import com.sprout.net.Injection
import com.sprout.ui.*

class MainViewModel : BaseViewModel(Injection.repository) {

    var fragment: MutableList<Fragment> = mutableListOf()

    var icon = listOf<Int>(
        R.drawable.selector_home,
        R.drawable.selector_search,
        R.drawable.selector_camera,
        R.drawable.selector_information,
        R.drawable.selector_me
    )

    init {
        fragment.add(HomeFragment())
        fragment.add(SearchFragment())
        fragment.add(CameraFragment())
        fragment.add(InformationFragment())
        fragment.add(MeFragment())
    }

}