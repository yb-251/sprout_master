package com.sprout.login

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sprout.R
import com.sprout.app.Constants
import com.sprout.base.BaseActivity
import com.sprout.databinding.ActivityLoginOkBinding
import com.sprout.utils.MyMmkv
import com.sprout.utils.SpUtils
import com.sprout.vm.BindLoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login_ok.*

class LoginOkActivity : BaseActivity<BindLoginActivityViewModel, ActivityLoginOkBinding>(
    R.layout.activity_login_ok,
    BindLoginActivityViewModel::class.java
) {


    override fun initView() {

        btn_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val username = input_username.text.toString()
                val password = input_pw.text.toString()
                if (username != null && password != null) {
                    var map = HashMap<String, String>()
                    map.put("username", username)
                    map.put("password", password)
                    mViewModel.login(map)
                } else {
                    Toast.makeText(this@LoginOkActivity, "账号密码不为空", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun initVM() {

        mViewModel.data.observe(this, Observer {
            if (it != null) {
                val code = it.code
                if (code == 200) {
                    val token = it.token
                    SpUtils.instance!!.setValue(Constants.token, it.token)
                    MyMmkv.setValue(Constants.token, token)
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else if (code == 601) {
                    Toast.makeText(this, "账号密码错误", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun initData() {

    }

    override fun initVariable() {

    }

}